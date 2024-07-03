package com.example.my_news.service;

import com.example.my_news.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Category findById(UUID id);

    Category save(Category category);

    Category update(Category category);

    void deleteById(UUID id);
}
