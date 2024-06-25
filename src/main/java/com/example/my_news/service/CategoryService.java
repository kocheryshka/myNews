package com.example.my_news.service;

import com.example.my_news.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAll();

    Category findById(UUID id);

    Category save(Category category);

    Category update(Category category);

    void deleteById(UUID id);
}
