package org.ms.kafka;

import org.ms.entityAndDTO.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(

        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {


}
