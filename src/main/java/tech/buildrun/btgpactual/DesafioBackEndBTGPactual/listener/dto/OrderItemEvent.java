package tech.buildrun.btgpactual.DesafioBackEndBTGPactual.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(String produto,
                             Integer quantidade,
                             BigDecimal preco) {
}
