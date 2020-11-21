package edu.keepeasy.restaurant_companion.repository;

import edu.keepeasy.restaurant_companion.domain.Meal;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MealRepoTest {
    @Autowired
    MealRepo mealRepo;
    Meal[] meals;

    @BeforeAll
    static void truncate() {
        try {
            Properties properties = new Properties();
            properties.load(MealRepoTest.class.getClassLoader().getResourceAsStream("application.properties"));
            Class.forName(properties.getProperty("spring.datasource.driver-class-name")).getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.test.url"),
                    properties.getProperty("spring.datasource.username"),
                    properties.getProperty("spring.datasource.password"));
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("schema.sql"));
            connection.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void mealAdding() {
        for (long i = 1; i < 6; i++) {
            Meal meal = new Meal(0, "Шашлык", 150, true);
            meal = mealRepo.create(meal);
            assertEquals("mealID", i, meal.getId());
        }
    }

    @Test
    @Order(2)
    void mealReading() {
        meals = mealRepo.readAll();
        for (Meal meal : meals) {
            System.out.println(meal.getId());
        }
        assertEquals("meals Count", 5, mealRepo.readAll().length);
    }

    @Test
    @Order(3)
    void mealDelete() {
        meals = mealRepo.readAll();
        mealRepo.delete(meals[2]);
        mealRepo.delete(meals[4]);
        assertEquals("meals Count", 3, mealRepo.readAll().length);
    }

    @Test
    @Order(4)
    void mealEditing() {
        meals = mealRepo.readAll();
        mealRepo.update(1, new Meal(1, "asd", 234, false));
        Meal t = mealRepo.read(1);
        assertEquals("id", (long) 1, t.getId());
        assertEquals("mealName", "asd", t.getMealName());
        assertEquals("mealCost", 234, t.getMealCost());
        assertEquals("mealAvailable", false, t.isMealAvailable());
    }

}