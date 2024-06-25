package com.example.my_news.service.impl;

import com.example.my_news.model.Category;
import com.example.my_news.repository.CategoryRepository;
import com.example.my_news.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Категория с id {0} не найдена!", id))
        );
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category existingCategory = findById(category.getId());
        if (category.getShortDesc() != null && !category.getShortDesc().equals(existingCategory.getShortDesc())){
            existingCategory.setShortDesc(category.getShortDesc());
        }

        if (category.getDescription() != null && !category.getDescription().equals(existingCategory.getDescription())){
            existingCategory.setDescription(category.getDescription());
        }

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }
}
