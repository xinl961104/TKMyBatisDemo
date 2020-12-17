package com.example.shop.controller;

import com.example.shop.mapper.ProductMapper;
import com.example.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(){
       return productMapper.getProducts();
    }


}
