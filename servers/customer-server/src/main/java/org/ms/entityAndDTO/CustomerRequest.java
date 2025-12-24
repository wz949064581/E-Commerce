package org.ms.entityAndDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        String id,
        @NotNull(message = "customer first name cannot be null")
        String firstName,
        @NotNull(message = "customer last name cannot be null")
        String lastName,
        @NotNull(message = "customer email cannot be null")
        @Email(message = "customer email is not valid")
        String email,
        Address address

){
}
