package br.com.letscode.ecommerce.domain.services;

import br.com.letscode.ecommerce.domain.models.OrderEntity;
import br.com.letscode.ecommerce.domain.models.OrderItemsEntity;
import br.com.letscode.ecommerce.domain.models.ProductEntity;
import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.OrderItemsRepository;
import br.com.letscode.ecommerce.domain.repositories.OrderRepository;
import br.com.letscode.ecommerce.domain.repositories.ProductRepository;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import br.com.letscode.ecommerce.exception.RolesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductRepository productRepository;


    public List<OrderItemsEntity> getOrderItems(Long user_id, Long order_id) {

        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        Optional<OrderEntity> orderExists = this.orderRepository.findById(order_id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }
        if (!orderExists.isPresent()) {
            throw  new RolesException("Pedido não existe");
        }

        if(orderExists.get().getUser().getId() != userExists.get().getId()) {
            throw new RolesException("O pedido não pertece a esse usuário");
        }

        List<OrderItemsEntity> orderItems = this.orderItemsRepository
                .findAll()
                .stream()
                .filter(orderItem -> orderItem.getOrder().getId() == orderExists.get().getId())
                .collect(Collectors.toList());

        return orderItems;
    }

    public OrderItemsEntity createOrderItem(OrderItemsEntity orderItem, Long user_id, Long order_id, Long product_id) {
        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        Optional<OrderEntity> orderExists = this.orderRepository.findById(order_id);

        Optional<ProductEntity> productExists = this.productRepository.findById(product_id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }
        if (!orderExists.isPresent()) {
            throw  new RolesException("Pedido não existe");
        }

        if (!productExists.isPresent()) {
            throw  new RolesException("Produto não existe");
        }

        if(orderExists.get().getUser().getId() != userExists.get().getId()) {
            throw new RolesException("O pedido não pertece a esse usuário");
        }

        orderItem.setOrder(orderExists.get());
        orderItem.setProduct(productExists.get());
        orderItem.setCreatedAt(ZonedDateTime.now());
        orderItem.setUpdatedAt(ZonedDateTime.now());

        return this.orderItemsRepository.save(orderItem);

    }

    public void removeOrderItem(Long user_id, Long order_id, Long id) {
        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        Optional<OrderEntity> orderExists = this.orderRepository.findById(order_id);

        Optional<OrderItemsEntity> orderItemExists = this.orderItemsRepository.findById(id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }
        if (!orderExists.isPresent()) {
            throw  new RolesException("Pedido não existe");
        }

        if(orderExists.get().getUser().getId() != userExists.get().getId()) {
            throw new RolesException("O pedido não pertece a esse usuário");
        }

        if (!orderItemExists.isPresent()) {
            throw  new RolesException("Item de pedido não existe");
        }

        this.orderItemsRepository.delete(orderItemExists.get());

    }


    public void updateOrderItem(OrderItemsEntity orderItem, Long user_id, Long order_id, Long id) {
        Optional<UserEntity> userExists = this.userRepository.findById(user_id);

        Optional<OrderEntity> orderExists = this.orderRepository.findById(order_id);

        Optional<OrderItemsEntity> orderItemExists = this.orderItemsRepository.findById(id);

        if(!userExists.isPresent()) {
            throw new RolesException("Usuário não existe.");
        }
        if (!orderExists.isPresent()) {
            throw  new RolesException("Pedido não existe");
        }

        if(orderExists.get().getUser().getId() != userExists.get().getId()) {
            throw new RolesException("O pedido não pertece a esse usuário");
        }

        if (!orderItemExists.isPresent()) {
            throw  new RolesException("Item de pedido não existe");
        }

        orderItem.setId(orderItemExists.get().getId());
        orderItem.setCreatedAt(orderItemExists.get().getCreatedAt());
        orderItem.setUpdatedAt(ZonedDateTime.now());
        this.orderItemsRepository.save(orderItem);

    }
}
