package org.ms.entityAndDTO;


import lombok.Builder;

@Builder
public record OrderLineResponse(

        Integer id,
        double quantity
) {
}
