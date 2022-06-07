package com.revature.repositories;

import java.util.List;

import com.revature.models.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    public List<Cart> findByUserId(int userId);
    
    @Transactional
    public void deleteByUserId(int userId);
}
