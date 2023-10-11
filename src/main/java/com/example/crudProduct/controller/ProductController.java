package com.example.crudProduct.controller;

import com.example.crudProduct.domain.product.Product;
import com.example.crudProduct.domain.product.ProductRepository;
import com.example.crudProduct.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    @GetMapping
    public ResponseEntity getAllProduct(){
        var allProducts = repository.findAllByActiveTrue();
    return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Validated RequestProduct data){
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Validated RequestProduct data){
        Optional<Product> optionalProduc =repository.findById(data.id());
        if (optionalProduc.isPresent()) {
            Product product = optionalProduc.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        }else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<Product> optionalProduc =repository.findById(id);
        if (optionalProduc.isPresent()) {
            Product product = optionalProduc.get();
            product.setActive(false);
            return ResponseEntity.noContent().build();
        }else {
            throw new EntityNotFoundException();
        }
    }
}
