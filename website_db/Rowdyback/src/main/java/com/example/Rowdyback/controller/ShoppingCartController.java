package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.ShoppingCart;
import com.example.Rowdyback.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<ShoppingCart> addItemToCart(@RequestParam Long userId, @RequestParam Long itemId, @RequestParam int quantity, @RequestParam int discountPercent) {
        ShoppingCart updatedCart = shoppingCartService.addItemToCart(userId, itemId, quantity, discountPercent);
        return ResponseEntity.ok(updatedCart);
    }

    @PostMapping("/remove")
    public ResponseEntity<ShoppingCart> removeItemFromCart(@RequestParam Long userId, @RequestParam Long itemId) {
        ShoppingCart updatedCart = shoppingCartService.removeItemFromCart(userId, itemId);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ShoppingCart> getCartForUser(@PathVariable Long userId) {
        return shoppingCartService.getCartForUser(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId) {
        shoppingCartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long userId) {
        Optional<ShoppingCart> cart = shoppingCartService.getCartForUser(userId);
        return cart.map(shoppingCart -> {
            Double total = shoppingCartService.calculateCartTotal(shoppingCart);
            return ResponseEntity.ok(total);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/subtotal/{userId}")
    public ResponseEntity<Double> getCartSubTotal(@PathVariable Long userId) {
        Optional<ShoppingCart> cart = shoppingCartService.getCartForUser(userId);
        return cart.map(shoppingCart -> {
            Double total = shoppingCartService.calculateCartSubTotal(shoppingCart);
            return ResponseEntity.ok(total);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/tax/{userId}")
    public ResponseEntity<Double> getCartTax(@PathVariable Long userId) {
        Optional<ShoppingCart> cart = shoppingCartService.getCartForUser(userId);
        return cart.map(shoppingCart -> {
            Double total = shoppingCartService.calculateCartTax(shoppingCart);
            return ResponseEntity.ok(total);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
