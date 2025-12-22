package org.ms.entityAndDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,

        @Positive(message = "Order amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "customerId is required")
        @NotEmpty(message = "customerId is required")
        @NotBlank(message = "customerId is required")
        String customerId,

        @NotEmpty(message = "products is required")
        List<PurchaseRequest> products
) {
}
