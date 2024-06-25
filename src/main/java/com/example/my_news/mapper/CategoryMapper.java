package com.example.my_news.mapper;

import com.example.my_news.model.Category;
import com.example.my_news.web.model.list.CategoryListResponse;
import com.example.my_news.web.model.single.CategoryResponse;
import com.example.my_news.web.model.upsert.UpsertCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.UUID;

//ToDo uses!!!!
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE/*, uses = !!!!!!!!!!*/)
public interface CategoryMapper {

    CategoryResponse categoryToResponse(Category category);

    Category requestToCategory(UpsertCategoryRequest request);

    Category requestToCategory(UUID id, UpsertCategoryRequest request);


    default CategoryListResponse categoryListToCategoryListResponse(List<Category> categories) {
        CategoryListResponse categoryListResponse = new CategoryListResponse();
        categoryListResponse.setCategories(
                categories.stream()
                        .map(this::categoryToResponse)
                        .toList()
        );
        return categoryListResponse;
    }


}
