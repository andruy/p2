package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findByUserId(int userId);
}
