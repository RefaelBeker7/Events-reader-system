package com.refael.events_reader_system.model.dto;

import com.refael.events_reader_system.model.Product;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private Long product_id;
    private String product_type;
    private double Price;
    private Date StartDate;
    private Date EndDate;
    private ProductEventDto productEventDto;

    public static ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProduct_id(product.getProduct_id());
        productDto.setProduct_type(product.getProduct_type());
        productDto.setPrice(product.getPrice());
        productDto.setStartDate(product.getStartDate());
        productDto.setEndDate(product.getEndDate());
//        if(Objects.nonNull(product.getProductEvent())){
//            productDto.setProductEventDto(Product.from(product.getProductEvent()));
//        }
        return productDto;
    }
}
