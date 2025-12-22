package org.ms.service;

import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.OrderLine;
import org.ms.entityAndDTO.OrderLineRequest;
import org.ms.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository repo;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {

        OrderLine orderLine = mapper.toOrderLine(request);
        return repo.save(orderLine).getId();
    }
}
