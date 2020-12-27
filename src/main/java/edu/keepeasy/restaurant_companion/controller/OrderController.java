package edu.keepeasy.restaurant_companion.controller;

import edu.keepeasy.restaurant_companion.domain.Order;
import edu.keepeasy.restaurant_companion.repository.MealRepo;
import edu.keepeasy.restaurant_companion.repository.OrderItemRepo;
import edu.keepeasy.restaurant_companion.repository.OrderRepo;
import edu.keepeasy.restaurant_companion.resource.MealResource;
import edu.keepeasy.restaurant_companion.resource.OrderItemResource;
import edu.keepeasy.restaurant_companion.resource.OrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/order")
public class OrderController {
    final
    OrderRepo repo;
    private final OrderItemRepo itemRepo;
    private final MealRepo mealRepo;

    public OrderController(@Autowired OrderRepo repo, @Autowired OrderItemRepo itemRepo, @Autowired MealRepo mealRepo) {
        this.repo = repo;
        this.itemRepo = itemRepo;
        this.mealRepo = mealRepo;
    }

    @PostMapping(value = "")
    OrderResource post(@RequestBody OrderResource resource) {
        Order order = repo.create(resource.toEntity());
        return (order == null) ? null : new OrderResource(order);
    }

    @GetMapping(value = "")
    OrderResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(repo.readAll())
                .map(order -> {
                    OrderResource orderResource = new OrderResource(order);
                    if (expand != null) {
                        orderResource.setItems(Arrays.stream(
                                itemRepo.readByOrderID(order.getId()))
                                .map(orderItem -> {
                                    OrderItemResource orderItemResource = new OrderItemResource(orderItem);
                                    orderItemResource.setMeal(
                                            new MealResource(mealRepo.read(orderItem.getMealID())));
                                    return orderItemResource;
                                })
                                .toArray(OrderItemResource[]::new));
                    }
                    return orderResource;
                })
                .toArray(OrderResource[]::new);
    }


    @GetMapping(value = "/{id}")
    OrderResource get(@PathVariable long id) {
        Order order = repo.read(id);
        return (order == null) ? null : new OrderResource(order);
    }

    @PutMapping(value = "/{id}")
    OrderResource put(@PathVariable long id, @RequestBody OrderResource resource) {
        Order order = repo.update(id, resource.toEntity());
        return (order == null) ? null : new OrderResource(order);
    }

    @DeleteMapping(value = "/{id}")
    OrderResource delete(@PathVariable long id) {
        Order entity = repo.read(id);
        Order order = (entity == null) ? null : repo.delete(entity);
        return (order == null) ? null : new OrderResource(order);
    }
}
