package com.example.Rowdyback.service;

import com.example.Rowdyback.model.Item;
import com.example.Rowdyback.model.ShoppingCart;
import com.example.Rowdyback.model.User;
import com.example.Rowdyback.model.CartItem;
import com.example.Rowdyback.repositories.ItemRepository;
import com.example.Rowdyback.repositories.ShoppingCartRepository;
import com.example.Rowdyback.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Map;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
                               UserRepository userRepository,
                               ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    // Method to add an item to the cart
    public ShoppingCart addItemToCart(Long userId, Long itemId, int quantity, int discountPercent) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));

        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElse(new ShoppingCart(user));

        cart.addItem(item, quantity, discountPercent); // Handling item adding logic internally

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

    // Method to update the quantity of an item in the cart
    public ShoppingCart updateItemQuantity(Long userId, Long itemId, int quantity) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with id: " + userId));

        cart.updateItemQuantity(itemId, quantity);

        cart.setTotalAmount(calculateCartTotal(cart));
        return shoppingCartRepository.save(cart);
    }

    // Method to get the cart for a user
    public Optional<ShoppingCart> getCartForUser(Long userId) {
        return shoppingCartRepository.findByUserUserId(userId);
    }

    // Method to clear the cart
    public void clearCart(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with id: " + userId));

        cart.clearItems();

        shoppingCartRepository.save(cart);
    }

    // Helper method to calculate the total amount of the cart
    public Double calculateCartTotal(ShoppingCart cart) {
        Double totalPrice = 0.0;
        Map<Long, CartItem> items = cart.getCartItems();

        for (CartItem item : items.values()) {
            if((item.getDiscountPercent() > 0) &&
                    (item.getDiscountPercent() < 100)) {
                double itemValue = (100 - item.getDiscountPercent())/100;
                totalPrice += (item.getItem().getPrice() * (itemValue)) * item.getQuantity();
            }
            else {
                totalPrice += item.getItem().getPrice() * item.getQuantity();
            }
        }
        return totalPrice*1.0825;
    }

    // Add other necessary methods and logic as required.
    public Double calculateCartSubTotal(ShoppingCart cart) {
        Double totalPrice = 0.0;
        Map<Long, CartItem> items = cart.getCartItems();

        for (CartItem item : items.values()) {
            if((item.getDiscountPercent() > 0) &&
                    (item.getDiscountPercent() < 100)) {
                double itemValue = (100 - item.getDiscountPercent())/100;
                totalPrice += (item.getItem().getPrice() * (itemValue)) * item.getQuantity();
            }
            else {
                totalPrice += item.getItem().getPrice() * item.getQuantity();
            }
        }
        return totalPrice;
    }
    public Double calculateCartTax(ShoppingCart cart) {
        Double totalPrice = 0.0;
        Map<Long, CartItem> items = cart.getCartItems();

        for (CartItem item : items.values()) {
            if((item.getDiscountPercent() > 0) &&
                    (item.getDiscountPercent() < 100)) {
                double itemValue = (100 - item.getDiscountPercent())/100;
                totalPrice += (item.getItem().getPrice() * (itemValue)) * item.getQuantity();
            }
            else {
                totalPrice += item.getItem().getPrice() * item.getQuantity();
            }
        }
        return totalPrice*0.0825;
    }
}
