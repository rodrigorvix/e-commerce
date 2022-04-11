package br.com.letscode.ecommerce.domain.services;


import br.com.letscode.ecommerce.domain.models.OrderEntity;
import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.OrderRepository;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import br.com.letscode.ecommerce.domain.util.OrderStatus;
import br.com.letscode.ecommerce.exception.RolesException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    public List<OrderEntity> getAllOrders(Long user_id) {

        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }

        List<OrderEntity> orders = this.orderRepository
                .findAll()
                .stream()
                .filter(order -> order.getUser().getId() == userExists.get().getId())
                .collect(Collectors.toList());

        return orders;

    }

    public OrderEntity createOrder(OrderEntity order, Long user_id) {

        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }

        order.setUser(userExists.get());
        order.setTotalPrice(BigDecimal.valueOf(0));
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(ZonedDateTime.now());
        order.setUpdatedAt(ZonedDateTime.now());

        return this.orderRepository.save(order);
    }

    public void removeOrder(Long user_id, Long id) {

        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }

        this.orderRepository.findById(id)
                .map( orderExists -> {
                    this.orderRepository.delete(orderExists);
                    return orderExists;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    public void setClosedStatusOrder(Long user_id, Long id) {

        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }

        this.orderRepository.findById(id)
                .map( orderExists -> {
                    orderExists.setStatus(OrderStatus.CLOSED);
                    this.orderRepository.save(orderExists);
                    return orderExists;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    public void setOpenStatusOrder(Long user_id, Long id) {
        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }

        this.orderRepository.findById(id)
                .map( orderExists -> {
                    orderExists.setStatus(OrderStatus.OPEN);
                    this.orderRepository.save(orderExists);
                    return orderExists;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }
}
