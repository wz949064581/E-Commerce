package org.ms.entityAndDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        String id,

        @NotNull(message = "firstName cannot be null")
        String firstName,

        @NotNull(message = "lastName cannot be null")
        String lastName,

        @NotNull(message = "email cannot be null")
        @Email(message = "email should be valid")
        String email
) {
}
