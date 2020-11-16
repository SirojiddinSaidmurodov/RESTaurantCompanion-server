package edu.keepeasy.restaurant_companion;

import edu.keepeasy.restaurant_companion.domain.Meal;
import edu.keepeasy.restaurant_companion.domain.User;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import edu.keepeasy.restaurant_companion.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class RestaurantCompanionApplicationTests {
    @Autowired
    MealRepo mealRepo;
    @Autowired
    private UserRepo userRepo;

    @Test
    void mealAdding() {
        Meal meal = new Meal(0, "Шашлык", 150, true);
        meal = mealRepo.create(meal);
        assertEquals("mealID", 1, meal.getId());
        assertEquals("mealName", "Шашлык", meal.getMealName());
        assertEquals("mealCost", 150, meal.getClass());
        assertEquals("mealAvailable", true, meal.isMealAvailable());
    }

    @Test
    void userAdding(){
        User user = new User(0, "Sirojiddin", "xakep13", "pass", User.UserType.ADMIN);
        user = userRepo.create(user);
        assertEquals("uerID", 1, user.getId());
    }

}
