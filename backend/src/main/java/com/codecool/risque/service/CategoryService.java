package com.codecool.risque.service;

import com.codecool.risque.model.Category;

import java.util.List;


public interface CategoryService {

    void save(Category category);
    List<Category> findAll();
}
