package com.example.shop.model;

import javax.persistence.*;

//Tkmybatis
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", table = "category")
    private Integer categoryId;
    private String categoryName;
    private String description;

    public Integer getCategoryID() {
        return categoryId;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryId = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
