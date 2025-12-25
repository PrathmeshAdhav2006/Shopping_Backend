package org.prathmesh.shopping_backend.Service;

import org.prathmesh.shopping_backend.Model.DTOs.OrderItemRequest;
import org.prathmesh.shopping_backend.Model.DTOs.OrderItemResponse;
import org.prathmesh.shopping_backend.Model.DTOs.OrderRequest;
import org.prathmesh.shopping_backend.Model.DTOs.OrderResponse;
import org.prathmesh.shopping_backend.Model.Order;
import org.prathmesh.shopping_backend.Model.OrderItem;
import org.prathmesh.shopping_backend.Model.Product;
import org.prathmesh.shopping_backend.Repository.OrderRepo;
import org.prathmesh.shopping_backend.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;


    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        String OID = "ORD"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
        order.setOrderId(OID);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("Placed");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> items = new ArrayList<>();
        for(OrderItemRequest item : orderRequest.items()){

            Product product = productRepo.findById(item.productId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found"));

            if (item.quantity() > product.getStockQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }
            product.setStockQuantity(product.getStockQuantity() - item.quantity());
            productRepo.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(item.quantity());
            orderItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
            orderItem.setOrder(order);

            items.add(orderItem);
        }

        order.setOrderItems(items);

        Order savedOrder = orderRepo.save(order);

        List<OrderItemResponse> orderItemResponseList = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()){

            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );

            orderItemResponseList.add(orderItemResponse);
        }

        OrderResponse response = new OrderResponse(
                savedOrder.getOrderId(),
                savedOrder.getCustomerName(),
                savedOrder.getEmail(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                orderItemResponseList
        );

        return response;
    }



    public List<OrderResponse> getAllOrders() {

        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {


            List<OrderItemResponse> itemResponses = new ArrayList<>();

            for(OrderItem item : order.getOrderItems()) {
                OrderItemResponse orderItemResponse = new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotalPrice()
                );
                itemResponses.add(orderItemResponse);

            }
            OrderResponse orderResponse = new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            );
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }


}
