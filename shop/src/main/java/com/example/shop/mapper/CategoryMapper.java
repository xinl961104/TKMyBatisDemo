package com.example.shop.mapper;

import com.example.shop.model.Category;
import com.example.shop.model.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//TKMybatis
@Repository
public interface CategoryMapper extends Mapper<Category> {
    @Select("select * from category where category_name = #{categoryName}")
    List<Category> selectByCategoryName(String categoryName);

    List<String> getProductNameByCategoryName(String categoryName);
}

