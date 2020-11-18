package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.repository.OrderItemRepo;
import edu.keepeasy.restaurant_companion.resource.OrderItemResource;
import edu.keepeasy.restaurant_companion.resource.OrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    final
    OrderItemRepo repo;

    public OrderItemController(@Autowired OrderItemRepo repo) {
        this.repo = repo;
    }

    @PostMapping(value = "")
    OrderItemResource post(@RequestBody OrderItemResource resource) {
        return new OrderItemResource(repo.create(resource.toEntity()));
    }

    @GetMapping(value = "")
    OrderItemResource[] getAll(@RequestBody OrderResource resource) {
        return Arrays.stream(repo.readAll(resource.toEntity()))
                .map(OrderItemResource::new)
                .toArray(OrderItemResource[]::new);
    }


    @GetMapping(value = "/{id}")
    OrderItemResource get(@PathVariable long id) {
        return new OrderItemResource(repo.read(id));
    }

    @PutMapping(value = "/{id}")
    OrderItemResource put(@PathVariable long id, @RequestBody OrderItemResource resource) {
        return new OrderItemResource(repo.update(id, resource.toEntity()));
    }

    @DeleteMapping(value = "/{id}")
    OrderItemResource delete(@PathVariable long id) {
        return new OrderItemResource(repo.delete(repo.read(id)));
    }
}
