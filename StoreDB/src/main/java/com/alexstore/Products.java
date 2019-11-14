package com.alexstore;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "products_to_orders",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id")}
    )
    List<Orders> orders = new ArrayList<>();

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private int id;

    @Column(name = "product_name")
    private String productName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Products() {
    }
}
