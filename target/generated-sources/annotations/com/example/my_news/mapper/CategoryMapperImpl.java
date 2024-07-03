package com.example.my_news.mapper;

import com.example.my_news.model.Category;
import com.example.my_news.web.model.single.CategoryResponse;
import com.example.my_news.web.model.upsert.UpsertCategoryRequest;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-02T14:32:58+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse categoryToResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setShortDesc( category.getShortDesc() );
        categoryResponse.setDescription( category.getDescription() );
        categoryResponse.setCreateAt( category.getCreateAt() );
        categoryResponse.setUpdateAt( category.getUpdateAt() );

        return categoryResponse;
    }

    @Override
    public Category requestToCategory(UpsertCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.shortDesc( request.getShortDesc() );
        category.description( request.getDescription() );

        return category.build();
    }

    @Override
    public Category requestToCategory(UUID id, UpsertCategoryRequest request) {
        if ( id == null && request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        if ( request != null ) {
            category.shortDesc( request.getShortDesc() );
            category.description( request.getDescription() );
        }
        category.id( id );

        return category.build();
    }
}
