package tech.buildrun.btgpactual.DesafioBackEndBTGPactual.controller.dto;

import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(Long orderId,
                            Long customerId,
                            BigDecimal total) {

    public static OrderResponse fromEntity(OrderEntity entity){
        return new OrderResponse(entity.getOrderId(), entity.getCustomerId(), entity.getTotal());
    }
}
