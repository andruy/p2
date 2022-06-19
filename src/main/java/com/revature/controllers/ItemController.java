package com.revature.controllers;

import java.util.List;

import com.revature.annotations.Authorized;
import com.revature.models.Item;
import com.revature.models.Role;
import com.revature.services.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
	
	// @Autowired
	// private AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }
    
    @Authorized(allowedRoles = {Role.CUSTOMER})
    @Timed(value = "additem.time") //custom metric
    @PostMapping("/{item}/{quantity}")
    public ResponseEntity<String> addToCart(@PathVariable String item, @PathVariable int quantity) {
        itemService.addToCart(item, quantity);
        return ResponseEntity.accepted().body("Item added to cart");
    }

    @Authorized(allowedRoles = {Role.CUSTOMER})
    @Timed(value = "cart.time") //custom metric
    @GetMapping("/cart")
    public ResponseEntity<List<Item>> getCartItems() {
        return ResponseEntity.ok(itemService.getCartItems());
    }

    @Authorized(allowedRoles = {Role.CUSTOMER})
    @Timed(value = "buy.time") //custom metric
    @PostMapping("/cart/buy")
    public ResponseEntity<String> placeOrder() {
        return ResponseEntity.accepted().body(itemService.placeOrder());
    }
}
