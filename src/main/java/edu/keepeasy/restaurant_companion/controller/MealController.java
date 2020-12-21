package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.domain.Meal;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import edu.keepeasy.restaurant_companion.resource.MealResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/meal")
public class MealController {
    final
    MealRepo repo;

    public MealController(@Autowired MealRepo repo) {
        this.repo = repo;
    }

    @PostMapping(value = "")
    MealResource post(@RequestBody MealResource mealResource) {
        Meal meal = repo.create(mealResource.toEntity());
        return (meal == null) ? null : new MealResource(meal);
    }

    @GetMapping(value = "")
    MealResource[] getAll() {
        return Arrays.stream(repo.readAll())
                .map(MealResource::new)
                .toArray(MealResource[]::new);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<MealResource> get(@PathVariable long id) {
        Meal meal = repo.read(id);
        return (meal == null) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new MealResource(meal), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<MealResource> put(@PathVariable long id, @RequestBody MealResource mealResource) {
        Meal meal = repo.update(id, mealResource.toEntity());
        return (meal == null) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new MealResource(meal), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<MealResource> delete(@PathVariable long id) {
        Meal entity = repo.read(id);
        Meal meal = (entity == null) ? null : repo.delete(entity);
        return (meal == null) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(new MealResource(meal), HttpStatus.OK);
    }


}
