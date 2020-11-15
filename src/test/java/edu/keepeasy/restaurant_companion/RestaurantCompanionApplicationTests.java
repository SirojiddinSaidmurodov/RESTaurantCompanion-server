package edu.keepeasy.restaurant_companion;

import edu.keepeasy.restaurant_companion.domain.Meal;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class RestaurantCompanionApplicationTests {
    @Autowired
    MealRepo mealRepo;

    @Test
    void mealAdding() {
        Meal meal = new Meal(0, "Шашлык", 150, true);
        meal = mealRepo.create(meal);
        assertEquals("mealID", 1, meal.id);
        assertEquals("mealName", "Шашлык", meal.getMealName());
        assertEquals("mealCost", 150, meal.getClass());
        assertEquals("mealAvailable", true, meal.isMealAvailable());
    }

    @Test
    void mealsReading() {
        Meal[] meals = mealRepo.readAll();
        for (Meal meal :
                meals) {
            System.out.println(meal.getMealCost());
        }
    }

    @Test
    void mealReading() {
        Meal meal = mealRepo.read(1);
        System.out.println(meal.getMealName());
    }

}
