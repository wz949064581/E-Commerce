package org.ms.entityAndDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

        @NotNull(message = "productId is required")
        Integer productId,

        @Positive(message = "quantity must be positive")
        double quantity
) {
}
