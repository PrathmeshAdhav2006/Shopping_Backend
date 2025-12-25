package org.prathmesh.shopping_backend.Model.DTOs;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productName,
        int quantity,
        BigDecimal totalPrice
 ){}
