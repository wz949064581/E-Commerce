package org.ms.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.ms.entityAndDTO.Customer;
import org.ms.entityAndDTO.CustomerRequest;
import org.ms.entityAndDTO.CustomerResponse;
import org.ms.exception.CustomerNotFoundException;
import org.ms.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {

        Customer customer = repo.save(mapper.toCustomer(request));
        return customer.getId();
    }


    public void updateCustomer(CustomerRequest request) {

        Customer customer = repo.findById(request.id()).orElseThrow(() ->
                new CustomerNotFoundException("Customer with id " + request.id() + " not found!"));
        mergeCustomer(customer, request);
        repo.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {

        return repo.findAll().stream()
                .map(mapper::toCustomerResponse).collect(Collectors.toList());
    }

    public Boolean findCustomerById(String id) {

        return repo.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {

        return repo.findById(id).map(mapper::toCustomerResponse).orElseThrow(() ->
                new CustomerNotFoundException("Customer with id " + id + " not found!"));
    }


    public void deleteById(String id) {

        repo.deleteById(id);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {

        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }


}
