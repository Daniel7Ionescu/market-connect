package com.dan.market_connect.models.entities;

import com.dan.market_connect.models.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @ElementCollection
    @CollectionTable(name = "customer_ratings",
            joinColumns = @JoinColumn(name = "customer_id"))
    @MapKeyColumn(name = "email")
    @Column(name = "ratings")
    private Map<Customer, Double> customerRatings;

    @ElementCollection
    @CollectionTable(name = "customer_reviews",
            joinColumns = @JoinColumn(name = "customer_id"))
    @MapKeyColumn(name = "email")
    @Column(name = "reviews")
    private Map<Customer, Double> customerReviews;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_category")
    private ProductCategory productCategory;

    @Column(name = "product_description")
    private String productDescription;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
}
