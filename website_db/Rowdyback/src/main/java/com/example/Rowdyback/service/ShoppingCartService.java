package com.example.Rowdyback.service;

import com.example.Rowdyback.model.Item;
import com.example.Rowdyback.model.ShoppingCart;
import com.example.Rowdyback.model.User;
import com.example.Rowdyback.repositories.ItemRepository;
import com.example.Rowdyback.repositories.ShoppingCartRepository;
import com.example.Rowdyback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Map;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    // Method to add an item to the cart
    public ShoppingCart addItemToCart(Long userId, Long itemId, int quantity) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Optional<Item> itemOptional = itemRepository.findById(itemId);
        Item item = itemOptional.orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));

        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElse(new ShoppingCart(user));

        // Adjust the quantity of the existing item or add a new one
        cart.addItem(itemId, quantity);
        cart.setTotalAmount(calculateCartTotal(cart));

        return shoppingCartRepository.save(cart);
    }

    // Method to remove an item from the cart
    public ShoppingCart removeItemFromCart(Long userId, Long itemId) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with id: " + userId));

        cart.removeItem(itemId);
        cart.setTotalAmount(calculateCartTotal(cart));

        return shoppingCartRepository.save(cart);
    }

    // Helper method to calculate the total amount of the cart
    public Double calculateCartTotal(ShoppingCart cart) {
        double totalPrice = 0.0;
        for (Map.Entry<Long, Integer> entry : cart.getItems().entrySet()) {
            Optional<Item> item = itemRepository.findById(entry.getKey());
            if (item.isPresent()) {
                totalPrice += item.get().getPrice() * entry.getValue();
            }
        }
        return totalPrice * 1.0825; // Assuming tax is included in the calculation
    }

    // Method to clear the cart
    public void clearCart(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with id: " + userId));

        cart.clearItems();
        cart.setTotalAmount(0.0); // Reset total when clearing the cart

        shoppingCartRepository.save(cart);
    }

    // Method to get the cart for a user
    public Optional<ShoppingCart> getCartForUser(Long userId) {
        return shoppingCartRepository.findByUserUserId(userId);
    }
}
