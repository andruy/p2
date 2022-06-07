package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.models.Cart;
import com.revature.models.Item;
import com.revature.models.Order;
import com.revature.repositories.CartRepository;
import com.revature.repositories.ItemRepository;
import com.revature.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartDAO;

    @Autowired
    private OrderRepository orderDAO;

    public List<Item> findAll() {
        return itemDAO.findAll();
    }

    public void addToCart(String item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            itemDAO.save(new Cart(userService.getCurrentUser().getId(), itemDAO.findByDescription(item).getId()));
        }

        getCartItems();
    }

    public List<Item> getCartItems() {
        List<Cart> items = cartDAO.findByUserId(userService.getCurrentUser().getId());
        List<Item> cartItems = new ArrayList<Item>();
        
        for (int i = 0; i < items.size(); i++) {
            cartItems.add(itemDAO.findById(items.get(i).getItemId()));
        }

        userService.getCurrentUser().setCart(cartItems);

        return cartItems;
    }

    public String placeOrder() {
        List<Item> itemsCart = userService.getCurrentUser().getCart();
        if (itemsCart.size() == 0) {
            return "Cart is empty";
        }
        String uuid = UUID.randomUUID().toString();
        int quantity = 0;
        String currentItem;
        while (itemsCart.size() > 0) {
            currentItem = itemsCart.get(0).getDescription();
            quantity++;
            if (itemsCart.size() > 1) {
                for (int i = 1; i < itemsCart.size(); i++) {
                    if (currentItem.equals(itemsCart.get(i).getDescription())) {
                        quantity++;
                    }
                }
            }
            orderDAO.save(new Order(userService.getCurrentUser().getId(),
                itemsCart.get(0).getId(),
                quantity,
                uuid)
            );
            if (quantity > 1) {
                while (quantity > 0) {
                    for (int i = 0; i < itemsCart.size(); i++) {
                        if (currentItem.equals(itemsCart.get(i).getDescription())) {
                            itemsCart.remove(i);
                            quantity--;
                        }
                    }
                }
            } else {
                itemsCart.remove(0);
                quantity = 0;
            }
        }
        cartDAO.deleteByUserId(userService.getCurrentUser().getId());

        getCartItems();
        
        return "Order placed";
    }
}
