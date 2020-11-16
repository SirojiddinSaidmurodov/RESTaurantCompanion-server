package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.repository.UserRepo;
import edu.keepeasy.restaurant_companion.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepo repo;

    @PostMapping(value = "")
    UserResource post(@RequestBody UserResource resource) {
        return new UserResource(repo.create(resource.toEntity()));
    }

    @GetMapping(value = "")
    UserResource[] getAll() {
        return Arrays.stream(repo.readAll())
                .map(UserResource::new)
                .toArray(UserResource[]::new);
    }


    @GetMapping(value = "/{id}")
    UserResource get(@PathVariable long id) {
        return new UserResource(repo.read(id));
    }

    @PutMapping(value = "/{id}")
    UserResource put(@PathVariable long id, @RequestBody UserResource resource) {
        return new UserResource(repo.update(id, resource.toEntity()));
    }

    @DeleteMapping(value = "/{id}")
    UserResource delete(@PathVariable long id) {
        return new UserResource(repo.delete(repo.read(id)));
    }

}
