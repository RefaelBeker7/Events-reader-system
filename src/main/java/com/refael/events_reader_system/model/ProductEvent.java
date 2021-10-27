package com.refael.events_reader_system.model;


import com.refael.events_reader_system.model.dto.ProductEventDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ProductEvent")
public class ProductEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String event_id;
    private String source_company;
    private String event_type;
    private Long insured_id;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "product_id")
    private List<Product> products = new ArrayList<>();

    public ProductEvent() {
    }

    public ProductEvent(String event_id, String source_company, String event_type, Long insured_id) {
        this.event_id = event_id;
        this.source_company = source_company;
        this.event_type = event_type;
        this.insured_id = insured_id;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }


    public static ProductEvent from(ProductEventDto productEventDto) {
        ProductEvent productEvent = new ProductEvent();
        productEvent.setEvent_id(productEventDto.getEvent_id());
        productEvent.setSource_company(productEventDto.getSource_company());
        productEvent.setEvent_type(productEventDto.getEvent_type());
        productEvent.setInsured_id(productEventDto.getInsured_id());
        return productEvent;
    }
}
