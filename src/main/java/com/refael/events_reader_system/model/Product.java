package com.refael.events_reader_system.model;

import com.refael.events_reader_system.model.dto.ProductDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;
    private String product_type;
    private double price;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private ProductEvent productEvent;

    public Product(String product_type, double price, Date startDate, Date endDate) {
        this.product_type = product_type;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Product() {

    }

    public static Product from(ProductDto productDto) {
        Product product = new Product();
        product.setProduct_type(productDto.getProduct_type());
        product.setPrice(productDto.getPrice());
        product.setStartDate(productDto.getStartDate());
        product.setEndDate(productDto.getEndDate());
        return product;
    }
}
