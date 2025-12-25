package org.prathmesh.shopping_backend.Model.DTOs;

import java.util.List;

public record OrderRequest(
        String customerName,
        String email,
        List<OrderItemRequest> items
 ) {}
