package br.com.letscode.ecommerce.domain.controllers;


import br.com.letscode.ecommerce.domain.models.OrderEntity;
import br.com.letscode.ecommerce.domain.models.OrderItemsEntity;
import br.com.letscode.ecommerce.domain.services.OrderItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/users/{user_id}/orders/{order_id}/order_items")
@RestController
public class OrderitemsController {

    private OrderItemsService orderItemsService;

    public OrderitemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping()
    public List<OrderItemsEntity> getOrderItemsByOrder(@PathVariable Long user_id, @PathVariable Long order_id) {

        List<OrderItemsEntity> orderItems = this.orderItemsService.getOrderItems(user_id, order_id);

        return orderItems;
    }

    @PostMapping("/{product_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItemsEntity createOrderItemByOrder (@RequestBody OrderItemsEntity orderItem,
                                               @PathVariable Long user_id,
                                               @PathVariable Long order_id,
                                               @PathVariable Long product_id){

        OrderItemsEntity orderItemsSaved = this.orderItemsService.createOrderItem(orderItem, user_id, order_id, product_id);

        return  orderItemsSaved;
    }

}
