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
    public ShoppingCart addItemToCart(Long userId, Long itemId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));

        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElse(new ShoppingCart(user));

        // Add item logic here
        cart.addItem(item); // Assuming item adding logic is handled internally

        cart.setTotalAmount(calculateCartTotal(cart));
        return shoppingCartRepository.save(cart);
    }

    // Method to remove an item from the cart
    public ShoppingCart removeItemFromCart(Long userId, Long itemId) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with id: " + userId));



        // Implement the logic to remove the item from the cart
        cart.removeItem(itemId); // Example method, implement in ShoppingCart class

        return shoppingCartRepository.save(cart);
    }

    // Method to update the quantity of an item in the cart
    public ShoppingCart updateItemQuantity(Long userId, Long itemId, int quantity) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with id: " + userId));

        // Implement the logic to update the item quantity in the cart
        cart.updateItemQuantity(itemId, quantity); // Example method, implement in ShoppingCart class

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

        // Clearing logic here
        cart.clearItems(); // Assuming you implement a clearItems method

        shoppingCartRepository.save(cart);
    }

    // Helper method to calculate the total amount of the cart
    private Double calculateCartTotal(ShoppingCart cart) {
        // Implement the logic to calculate the total amount
        // This might involve summing the price*quantity of all items in the cart
        // for each item in cart, do something like: total += item.getPrice() * item.getQuantity();
        Double totalPrice=0.0;
        Map<Long,CartItem> map = cart.getCartItems();

        for (Map.Entry<Long, CartItem> items : map.entrySet()) {
            totalPrice = totalPrice + (items.getValue().getItem().getPrice() * items.getValue().getQuantity());
        }
        return totalPrice;
    }

    // Add other necessary methods and logic as required.
}
