package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import com.revature.models.Cart;
import com.revature.models.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    public Item findByDescription(String description);

    public Item findById(int id);

    public Optional<Void> save(Cart item);

    @Query(value = "insert into carts (user_id, item_id) values (?1, ?2)", nativeQuery = true)
    public Optional<Void> addToCart(int userId, int itemId);

    @Query(value = "select * from carts c where user_id = :userId", nativeQuery = true)
    public List<Item> getCartItems(int userId);
}
