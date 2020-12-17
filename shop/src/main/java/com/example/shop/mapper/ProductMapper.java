package com.example.shop.mapper;

//传统Mapper with @Mapper


import com.example.shop.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getProducts();
}