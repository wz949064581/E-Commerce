package org.ms.repository;

import org.ms.entityAndDTO.OrderLine;
import org.ms.entityAndDTO.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {


    List<OrderLine> findAllByOrderId(Integer orderId);
}
