package org.ms.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment-service", url = "http://payment-service/api/v1/payments")
public class PaymentClient {


}
