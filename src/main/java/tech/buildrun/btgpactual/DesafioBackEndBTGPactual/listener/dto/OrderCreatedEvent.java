package tech.buildrun.btgpactual.DesafioBackEndBTGPactual.listener.dto;

import java.util.List;

public record OrderCreatedEvent(Long codigoPedido,
                                Long codigoClente,
                                List<OrderItemEvent> itens) {
}
