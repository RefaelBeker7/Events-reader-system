package com.refael.events_reader_system.service;

import com.refael.events_reader_system.model.Product;
import com.refael.events_reader_system.model.ProductEvent;
import com.refael.events_reader_system.model.exception.ProductEventNotFoundException;
import com.refael.events_reader_system.model.exception.ProductIsAlreadyAssignedException;
import com.refael.events_reader_system.repository.ProductEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductEventService {

    private final ProductEventRepository productEventRepository;
    private final ProductService productService;

    @Autowired
    public ProductEventService(ProductEventRepository productEventRepository, ProductService productService) {
        this.productEventRepository = productEventRepository;
        this.productService = productService;
    }

    public ProductEvent addProductEvent(ProductEvent productEvent) {
        return productEventRepository.save(productEvent);
    }

    public List<ProductEvent> getProductsEvent() {
        return StreamSupport
                .stream(productEventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ProductEvent getProductEvent(Long id) {
        return productEventRepository.findById(id).orElseThrow(() ->
                new ProductEventNotFoundException(id));
    }

    public ProductEvent deleteProductEvent(Long id) {
        ProductEvent productEvent = getProductEvent(id);
        productEventRepository.delete(productEvent);
        return productEvent;
    }

    @Transactional
    public ProductEvent editProductEvent(Long id, ProductEvent productEvent) {
        ProductEvent productEventToEdit = getProductEvent(id);
        productEventToEdit.setInsured_id(productEvent.getInsured_id());
        productEventToEdit.setEvent_type(productEvent.getEvent_type());
        return productEventToEdit;
    }

    @Transactional
    public ProductEvent addProductToProductEvent(Long eventId, Long productId) {
        ProductEvent productEvent = getProductEvent(eventId);
        Product product = productService.getProduct(eventId);
        if (Objects.nonNull(product.getProductEvent())) {
            throw new ProductIsAlreadyAssignedException(productId,
                    product.getProductEvent().getEvent_id());
        }
        productEvent.addProduct(product);
        product.setProductEvent(productEvent);
        return productEvent;
    }

    @Transactional
    public ProductEvent removeItemFromCart(Long eventId, Long productId) {
        ProductEvent productEvent = getProductEvent(eventId);
        Product product = productService.getProduct(productId);
        productEvent.removeProduct(product);
        return productEvent;
    }
}
