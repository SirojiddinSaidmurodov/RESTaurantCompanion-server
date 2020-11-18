package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.domain.User;
import edu.keepeasy.restaurant_companion.repository.UserRepo;
import edu.keepeasy.restaurant_companion.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {
    final
    UserRepo repo;

    public UserController(@Autowired UserRepo repo) {
        this.repo = repo;
    }

    @PostMapping(value = "")
    UserResource post(@RequestBody UserResource resource) {
        User user = repo.create(resource.toEntity());
        return user == null ? null : new UserResource(user);
    }

    @GetMapping(value = "")
    UserResource[] getAll() {
        return Arrays.stream(repo.readAll())
                .map(UserResource::new)
                .toArray(UserResource[]::new);
    }


    @GetMapping(value = "/{id}")
    UserResource get(@PathVariable long id) {
        User user = repo.read(id);
        return user == null ? null : new UserResource(user);
    }

    @PutMapping(value = "/{id}")
    UserResource put(@PathVariable long id, @RequestBody UserResource resource) {
        User user = repo.update(id, resource.toEntity());
        return user == null ? null : new UserResource(user);
    }

    @DeleteMapping(value = "/{id}")
    UserResource delete(@PathVariable long id) {
        User entity = repo.read(id);
        User user = entity == null ? null : repo.delete(entity);
        return user == null ? null : new UserResource(user);
    }

}
