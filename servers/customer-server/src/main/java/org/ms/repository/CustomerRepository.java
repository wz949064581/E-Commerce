package org.ms.repository;

import org.ms.entityAndDTO.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {


}
