package org.ms.kafka;

import org.ms.entityAndDTO.CustomerResponse;
import org.ms.entityAndDTO.PaymentMethod;
import org.ms.entityAndDTO.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
