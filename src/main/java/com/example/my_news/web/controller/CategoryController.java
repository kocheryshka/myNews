package com.example.my_news.web.controller;

import com.example.my_news.mapper.CategoryMapper;
import com.example.my_news.model.Category;
import com.example.my_news.service.CategoryService;
import com.example.my_news.web.model.list.CategoryListResponse;
import com.example.my_news.web.model.single.CategoryResponse;
import com.example.my_news.web.model.upsert.UpsertCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(){
        return ResponseEntity.ok(categoryMapper.categoryListToCategoryListResponse(categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(categoryMapper.categoryToResponse(categoryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody UpsertCategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryMapper.categoryToResponse(
                        categoryService.save(
                                categoryMapper.requestToCategory(request)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID id, @RequestBody UpsertCategoryRequest request){
        return ResponseEntity.ok(
                categoryMapper.categoryToResponse(
                        categoryService.update(
                                categoryMapper.requestToCategory(id, request)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        categoryService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
