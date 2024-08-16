package tech.buildrun.btgpactual.DesafioBackEndBTGPactual.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.listener.dto.OrderCreatedEvent;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.service.OrderService;

import static tech.buildrun.btgpactual.DesafioBackEndBTGPactual.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreaterdListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreaterdListener.class);

    public final OrderService orderService;

    public OrderCreaterdListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        logger.info("Message consumed: {}",message);

        orderService.save(message.getPayload());
    }

}
