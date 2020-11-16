package edu.keepeasy.restaurant_companion;

import edu.keepeasy.restaurant_companion.domain.Meal;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RepoTest {
    @Autowired
    MealRepo mealRepo;
    Meal[] meals;

    @BeforeAll
    static void truncate() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String dbUrl = "jdbc:mysql://localhost/restaurant?serverTimezone=Europe/Moscow&useSSL=false";
            Connection connection = DriverManager.getConnection(dbUrl, "root", "131214");
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("schema.sql"));
            connection.close();
        } catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException throwables) {
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
