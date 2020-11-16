package edu.keepeasy.restaurant_companion;

import edu.keepeasy.restaurant_companion.domain.Meal;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import edu.keepeasy.restaurant_companion.repository.UserRepo;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class RepoTest {
    @Autowired
    MealRepo mealRepo;
    Meal[] meals;
    @Autowired
    private UserRepo userRepo;

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
