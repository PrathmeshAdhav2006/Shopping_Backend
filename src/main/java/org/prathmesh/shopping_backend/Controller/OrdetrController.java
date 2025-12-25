package org.prathmesh.shopping_backend.Controller;

import org.prathmesh.shopping_backend.Model.DTOs.OrderRequest;
import org.prathmesh.shopping_backend.Model.DTOs.OrderResponse;
import org.prathmesh.shopping_backend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrdetrController {

    @Autowired
    OrderService orderService;

    @PostMapping("orders/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse , HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        List<OrderResponse> responses = orderService.getAllOrders();
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

}
