package edu.keepeasy.restaurant_companion;

import edu.keepeasy.restaurant_companion.domain.Meal;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import edu.keepeasy.restaurant_companion.repository.UserRepo;
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
    void mealAdding() {
        for (long i = 1; i < 6; i++) {
            Meal meal = new Meal(0, "Шашлык", 150, true);
            meal = mealRepo.create(meal);
            assertEquals("mealID", i, meal.getId());
        }
    }

    @Test
    void mealaReading() {
        meals = mealRepo.readAll();
        for (Meal meal : meals) {
            System.out.println(meal.getId());
        }
    }

    @Test
    void mealDelete() {
        meals = mealRepo.readAll();
        mealRepo.delete(meals[2]);
        mealRepo.delete(meals[4]);
        mealaReading();
    }

}
