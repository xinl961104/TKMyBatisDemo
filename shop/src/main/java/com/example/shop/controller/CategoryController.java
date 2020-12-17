package com.example.shop.controller;

import com.example.shop.mapper.CategoryMapper;
import com.example.shop.model.Category;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryMapper categoryMapper;

    //selectall - 按照categoryName 降序排列
    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        Example example = new Example(Category.class);
        example.orderBy("categoryName").desc();
        return categoryMapper.selectByExample(example);
    }

    //selectByExample 通过Example实现筛选操作，某种程度上代替了SQL中的where操作
    @PostMapping("/getSelectedCategory/{id}")
    public List<Category> getSelectedCategory(@PathVariable("id") String id){
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("categoryId", id);
        //criteria.andLike("categoryName", "%ce%");
        //criteria.andGreaterThan("categoryId", "1");//大于
        //criteria.andLessThan("categoryId", "2");//小于
        //criteria.andIsNotNull("categoryId");
        //criteria.andBetween("date", "1956/01/08", "1966/10/21");
        return categoryMapper.selectByExample(example);
    }

    //insert 不论设置多少个字段，统一都要添加一遍，不论你设置几个字段，即使是一个
    @PostMapping("/saveCategory")
    public void insertTest(@RequestBody Category category) {
        categoryMapper.insert(category);
    }

    //insertSelective 仅添加设置了的字段，其他字段可以为null
    @PostMapping("/saveSelectedCategory")
    public void insertSelectedCategory(@RequestBody Category category){
        categoryMapper.insertSelective(category);
    }

    //updateSelectiveCategory 中update仅更新设置了的字段，其他字段可以为null
    @PostMapping("/updateExampleSelectedCategory")
    public void updateExampleSelectedCategory(@RequestBody Category category){
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",category.getCategoryID());
        categoryMapper.updateByExampleSelective(category,example);
        }

    //updateByExample 不论设置多少个字段，统一都要更新一遍
    @PostMapping("/updateExampleCategory")
    public void updateSelectedCategory(@RequestBody Category category){
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",category.getCategoryID());
        categoryMapper.updateByExample(category,example);
    }

    //custom mapper写法1(annotation)
    @PostMapping("/getCategoryByName/{name}")
    public List<Category> getCategoryByName(@PathVariable("name") String name){
        return categoryMapper.selectByCategoryName(name);
    }

    //custom mapper写法2(XML)
    @PostMapping("/getProductNameByCategoryName/{name}")
    public List<String> getProductNameByCategoryName(@PathVariable("name") String name){
        return categoryMapper.getProductNameByCategoryName(name);
    }

    //mybatis/tk mybatis 分页查询
    @PostMapping("/getCategoryPage/{page}/{size}")
    public PageInfo<Category> getCategoryPage(@PathVariable("page") int page, @PathVariable("size") int size){
        PageHelper.startPage(page,size);
        List<Category> categories = categoryMapper.selectAll();
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        return pageInfo;
    }
}
