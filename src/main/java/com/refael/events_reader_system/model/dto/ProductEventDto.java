package com.refael.events_reader_system.model.dto;

import com.refael.events_reader_system.model.ProductEvent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductEventDto {
    private String event_id;
    private String source_company;
    private String event_type;
    private Long insured_id;
    private List<ProductDto> productDto = new ArrayList<>();

    public static ProductEventDto from(ProductEvent productEvent) {
        ProductEventDto productEventDto = new ProductEventDto();
        productEventDto.setEvent_id(productEvent.getEvent_id());
        productEventDto.setSource_company(productEvent.getSource_company());
        productEventDto.setEvent_type(productEvent.getEvent_type());
        productEventDto.setInsured_id(productEvent.getInsured_id());
        productEventDto.setProductDto(productEvent.getProducts().stream().map(ProductDto::from).collect(Collectors.toList()));
        return productEventDto;
    }
}
