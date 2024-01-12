package com.dan.market_connect.models.dtos;

import com.dan.market_connect.models.ProductCategory;
import com.dan.market_connect.models.entities.Customer;
import lombok.Data;

import java.util.Map;

@Data
public class ProductDTO {

    private Long id;

    private String productName;

    private Map<Customer, Double> customerRatings;

    private Map<Customer, Double> customerReviews;

    private Double productPrice;

    private ProductCategory productCategory;

    private String productDescription;
}