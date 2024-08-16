package tech.buildrun.btgpactual.DesafioBackEndBTGPactual.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.controller.dto.OrderResponse;
import tech.buildrun.btgpactual.DesafioBackEndBTGPactual.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pegeRequest);
}
