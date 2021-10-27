package com.refael.events_reader_system.controller;

import com.refael.events_reader_system.model.Product;
import com.refael.events_reader_system.model.dto.ProductDto;
import com.refael.events_reader_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody final ProductDto productDto) {
        Product product = productService.addProduct(Product.from(productDto));
        return new ResponseEntity<>(ProductDto.from(product), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProduct() {
        List<Product> products = productService.getProducts();
        List<ProductDto> productDtoList = products.stream().map(ProductDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable final Long id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(ProductDto.from(product), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable final Long id) {
        Product product = productService.deleteProduct(id);
        return new ResponseEntity<>(ProductDto.from(product), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable final Long id,
                                                  @RequestBody final ProductDto productDto) {
        Product editedItem = productService.editProduct(id, Product.from(productDto));
        return new ResponseEntity<>(ProductDto.from(editedItem), HttpStatus.OK);
    }
}
