package edu.keepeasy.restaurant_companion;

import edu.keepeasy.restaurant_companion.domain.Meal;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantCompanionApplicationTests {
    @Autowired
    MealRepo mealRepo;
    @Test
    void mealAdding(){
        Meal meal = new Meal(0, "Шашлык", 150, true);
        mealRepo.create(meal);
        System.out.println(meal.getId());
    }
    @Test
    void mealsReading(){
        Meal[] meals = mealRepo.readAll();
        for (Meal meal :
                meals) {
            System.out.println(meal.getMealCost());
        }
    }
    @Test
    void mealReading(){
        Meal meal = mealRepo.read(1);
        System.out.println(meal.getMealName());
    }

}
