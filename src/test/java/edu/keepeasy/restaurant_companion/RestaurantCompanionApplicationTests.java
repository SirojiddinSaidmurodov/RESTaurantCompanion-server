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
        mealRepo.create(new Meal(0, "Шашлык", 150, true));
    }

}
