package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.Item;
import com.example.Rowdyback.model.ShoppingCart;
import com.example.Rowdyback.controller.AddItemRequest;
import com.example.Rowdyback.repositories.ItemRepository;
import com.example.Rowdyback.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/Cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    private ItemRepository itemRepository;

    @PostMapping("/add")
    public ResponseEntity<ShoppingCart> addItemToCart(@RequestBody AddItemRequest addItemRequest) {
        ShoppingCart updatedCart = shoppingCartService.addItemToCart(
                addItemRequest.getUserId(),
                addItemRequest.getItemId(),
                addItemRequest.getQuantity() // Removed the discount parameter
        );
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

    //
    // Method to calculate the subtotal of the cart
    public Double calculateCartSubTotal(ShoppingCart cart) {
        double subtotal = 0.0;
        for (Map.Entry<Long, Integer> entry : cart.getItems().entrySet()) {
            var item = itemRepository.findById(entry.getKey());
            if (item.isPresent()) {
                subtotal += item.get().getPrice() * entry.getValue();
            }
        }
        return subtotal;
    }

    // Method to calculate the tax amount of the cart
    public Double calculateCartTax(ShoppingCart cart) {
        double subtotal = calculateCartSubTotal(cart);
        return subtotal * 0.0825; // Assuming 8.25% tax rate
    }

}


