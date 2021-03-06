package br.com.letscode.ecommerce.domain.controllers;

import br.com.letscode.ecommerce.domain.models.OrderEntity;
import br.com.letscode.ecommerce.domain.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users/{user_id}/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }*/

   @GetMapping()
   public List<OrderEntity> getOrdersByUser(@PathVariable Long user_id) {

       List<OrderEntity> orders = this.orderService.getAllOrders(user_id);

       return orders;
   }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrderEntity createOrderByUser (@RequestBody OrderEntity order, @PathVariable Long user_id){

        OrderEntity orderSaved = this.orderService.createOrder(order, user_id);

        return  orderSaved;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderByUser(@PathVariable Long user_id, @PathVariable Long id) {

        this.orderService.removeOrder(user_id, id);

    }

    @PatchMapping("/{id}/closed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setClosedStatusOrder(@PathVariable Long user_id, @PathVariable Long id) {

        this.orderService.setClosedStatusOrder(user_id, id);

    }
    @PatchMapping("/{id}/open")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setOpenStatusOrder(@PathVariable Long user_id, @PathVariable Long id) {

        this.orderService.setOpenStatusOrder(user_id, id);

    }

}
