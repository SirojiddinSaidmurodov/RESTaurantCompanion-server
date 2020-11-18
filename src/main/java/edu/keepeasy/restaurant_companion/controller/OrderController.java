package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.domain.Order;
import edu.keepeasy.restaurant_companion.repository.OrderRepo;
import edu.keepeasy.restaurant_companion.resource.OrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/order")
public class OrderController {
    final
    OrderRepo repo;

    public OrderController(@Autowired OrderRepo repo) {
        this.repo = repo;
    }

    @PostMapping(value = "")
    OrderResource post(@RequestBody OrderResource resource) {
        Order order = repo.create(resource.toEntity());
        return order == null ? null : new OrderResource(order);
    }

    @GetMapping(value = "")
    OrderResource[] getAll() {
        return Arrays.stream(repo.readAll())
                .map(OrderResource::new)
                .toArray(OrderResource[]::new);
    }


    @GetMapping(value = "/{id}")
    OrderResource get(@PathVariable long id) {
        Order order = repo.read(id);
        return order == null ? null : new OrderResource(order);
    }

    @PutMapping(value = "/{id}")
    OrderResource put(@PathVariable long id, @RequestBody OrderResource resource) {
        Order order = repo.update(id, resource.toEntity());
        return order == null ? null : new OrderResource(order);
    }

    @DeleteMapping(value = "/{id}")
    OrderResource delete(@PathVariable long id) {
        Order order = repo.delete(repo.read(id));
        return order == null ? null : new OrderResource(order);
    }
}
