package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.domain.OrderItem;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import edu.keepeasy.restaurant_companion.repository.OrderItemRepo;
import edu.keepeasy.restaurant_companion.resource.MealResource;
import edu.keepeasy.restaurant_companion.resource.OrderItemResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    final
    OrderItemRepo repo;
    final MealRepo mealRepo;

    public OrderItemController(@Autowired OrderItemRepo repo, @Autowired MealRepo mealRepo) {
        this.repo = repo;
        this.mealRepo = mealRepo;
    }

    @PostMapping(value = "")
    OrderItemResource post(@RequestBody OrderItemResource resource) {
        OrderItem orderItem = repo.create(resource.toEntity());
        return (orderItem == null) ? null : new OrderItemResource(orderItem);
    }

    @GetMapping(value = "")
    OrderItemResource[] getAll(@RequestParam(required = false) Long orderID,
                               @RequestParam(required = false) Object expand) {
        OrderItem[] orderItems = (orderID == null) ?
                repo.readAll() :
                repo.readByOrderID(orderID);
        return Arrays.stream(orderItems)
                .map(orderItem -> {
                    OrderItemResource orderItemResource = new OrderItemResource(orderItem);
                    if (expand != null) {
                        orderItemResource.setMeal(
                                new MealResource(mealRepo.read(orderItem.getMealID())));
                    }
                    return orderItemResource;
                })
                .toArray(OrderItemResource[]::new);
    }


    @GetMapping(value = "/{id}")
    OrderItemResource get(@PathVariable long id) {
        OrderItem orderItem = repo.read(id);
        return (orderItem == null) ? null : new OrderItemResource(orderItem);
    }

    @PutMapping(value = "/{id}")
    OrderItemResource put(@PathVariable long id, @RequestBody OrderItemResource resource) {
        OrderItem orderItem = repo.update(id, resource.toEntity());
        return (orderItem == null) ? null : new OrderItemResource(orderItem);
    }

    @DeleteMapping(value = "/{id}")
    OrderItemResource delete(@PathVariable long id) {
        OrderItem entity = repo.read(id);
        OrderItem orderItem = (entity == null) ? null : repo.delete(entity);
        return (orderItem == null) ? null : new OrderItemResource(orderItem);
    }
}
