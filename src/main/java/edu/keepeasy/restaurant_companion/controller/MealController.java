package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.repository.MealRepo;
import edu.keepeasy.restaurant_companion.resource.MealResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/meal")
public class MealController {
    @Autowired
    MealRepo repo;

    @PostMapping(value = "")
    MealResource post(@RequestBody MealResource mealResource) {
        return new MealResource(repo.create(mealResource.toEntity()));
    }

    @GetMapping(value = "")
    MealResource[] getAll() {
        return Arrays.stream(repo.readAll())
                .map(MealResource::new)
                .toArray(MealResource[]::new);
    }

    @GetMapping(value = "/{id}")
    MealResource get(@PathVariable long id) {
        return new MealResource(repo.read(id));
    }

    @PutMapping(value = "/{id}")
    MealResource put(@PathVariable long id, @RequestBody MealResource mealResource) {
        return new MealResource(repo.update(id, mealResource.toEntity()));
    }

    @DeleteMapping(value = "/{id}")
    MealResource delete(@PathVariable long id) {
        return new MealResource(repo.delete(repo.read(id)));
    }


}
