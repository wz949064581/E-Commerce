package org.ms.service;


import lombok.RequiredArgsConstructor;
import org.ms.entityAndDTO.Payment;
import org.ms.entityAndDTO.PaymentNotificationRequest;
import org.ms.entityAndDTO.PaymentRequest;
import org.ms.repository.PaymentRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepo repo;
    private final PaymentMapper mapper;
    private final NotificationProducer producer;

    public Integer createPayment(PaymentRequest request) {

        Payment payment = repo.save(mapper.toPayment(request));
        producer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }


}
