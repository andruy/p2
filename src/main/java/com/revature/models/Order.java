package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int itemId;
    private int quantity;
    private String orderNumber;

    public Order(int userId, int itemId, int quantity, String orderNumber) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.orderNumber = orderNumber;
    }
}
