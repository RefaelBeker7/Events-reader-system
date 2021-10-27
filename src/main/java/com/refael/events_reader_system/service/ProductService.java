package com.refael.events_reader_system.service;

import com.refael.events_reader_system.model.Product;
import com.refael.events_reader_system.model.exception.ProductNotFoundException;
import com.refael.events_reader_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(id));
    }

    public Product deleteProduct(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        return product;
    }

    @Transactional
    public Product editProduct(Long id, Product product) {
        Product productToEdit = getProduct(id);
        productToEdit.setProduct_type(product.getProduct_type());
        productToEdit.setPrice(product.getPrice());
        productToEdit.setStartDate(product.getStartDate());
        productToEdit.setEndDate(product.getEndDate());
        return productToEdit;
    }
}
