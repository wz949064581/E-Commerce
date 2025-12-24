package org.ms.entityAndDTO;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

         Integer id,
         @NotNull(message = "product name cannot be null")
         String name,
         @NotNull(message = "product description cannot be null")
         String description,
         @Positive(message = "available quantity must be positive")
         double availableQuantity,
         @Positive(message = "price must be positive")
         BigDecimal price,
         @NotNull(message = "categoryId cannot be null")
         Integer categoryId


) {
}
