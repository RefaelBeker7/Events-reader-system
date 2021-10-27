package com.refael.events_reader_system.controller;

import com.refael.events_reader_system.model.ProductEvent;
import com.refael.events_reader_system.model.dto.ProductEventDto;
import com.refael.events_reader_system.service.ProductEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ProductEvent")
public class ProductEventController {

    private final ProductEventService productEventService;

    @Autowired
    public ProductEventController(ProductEventService productEventService) {
        this.productEventService = productEventService;
    }

    @PostMapping
    public ResponseEntity<ProductEventDto> addProductEvent(@RequestBody final ProductEventDto productEventDto) {
        ProductEvent productEvent = productEventService.addProductEvent(ProductEvent.from(productEventDto));
        return new ResponseEntity<>(ProductEventDto.from(productEvent), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductEventDto>> getProductsEvent() {
        List<ProductEvent> productEvents = productEventService.getProductsEvent();
        List<ProductEventDto> productEventDtoList = productEvents.stream().map(ProductEventDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(productEventDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductEventDto> getProductEvent(@PathVariable final Long id) {
        ProductEvent productEvent = productEventService.getProductEvent(id);
        return new ResponseEntity<>(ProductEventDto.from(productEvent), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ProductEventDto> deleteProductEvent(@PathVariable final Long id) {
        ProductEvent productEvent = productEventService.deleteProductEvent(id);
        return new ResponseEntity<>(ProductEventDto.from(productEvent), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ProductEventDto> editProductEvent(@PathVariable final Long id,
                                                            @RequestBody final ProductEventDto productEventDto) {
        ProductEvent productEvent = productEventService.editProductEvent(id, ProductEvent.from(productEventDto));
        return new ResponseEntity<>(ProductEventDto.from(productEvent), HttpStatus.OK);
    }

    @PostMapping(value = "{eventId}/products/{productId}/add")
    public ResponseEntity<ProductEventDto> addProductToProductEvent(@PathVariable final Long eventId,
                                                                    @PathVariable final Long productId) {
        ProductEvent productEvent = productEventService.addProductToProductEvent(eventId, productId);
        return new ResponseEntity<>(ProductEventDto.from(productEvent), HttpStatus.OK);
    }

    @DeleteMapping(value = "{eventId}/products/{productId}/remove")
    public ResponseEntity<ProductEventDto> removeProductFromProductEvent(@PathVariable final Long eventId,
                                                                         @PathVariable final Long productId) {
        ProductEvent productEvent = productEventService.removeItemFromCart(eventId, productId);
        return new ResponseEntity<>(ProductEventDto.from(productEvent), HttpStatus.OK);
    }
}
