package org.ms.entityAndDTO;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

    @NotNull(message = "Product ID cannot be null")
    Integer productId,

    @NotNull(message = "quantity cannot be null")
    double quantity

) {
}
