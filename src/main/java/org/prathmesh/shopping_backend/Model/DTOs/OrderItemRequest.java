package org.prathmesh.shopping_backend.Model.DTOs;

public record OrderItemRequest(
        int productId,
        int quantity
) {}
