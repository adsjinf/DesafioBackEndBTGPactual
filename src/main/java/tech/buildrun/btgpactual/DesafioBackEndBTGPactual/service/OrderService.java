package tech.buildrun.btgpactual.DesafioBackEndBTGPactual.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.controller.dto.OrderResponse;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.entity.OrderEntity;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.entity.OrderItem;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.listener.dto.OrderCreatedEvent;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event){
        var entity = new OrderEntity();

        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setItens(getOrderItens(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pegeRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pegeRequest);

        return orders.map(OrderResponse::fromEntity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItens(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco()))
                .toList();
    }

}

