package com.example.my_news.repository;

import com.example.my_news.model.Category;
import com.example.my_news.model.News;
import com.example.my_news.model.User;
import com.example.my_news.web.model.single.NewsFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilterRequest filter){
        return Specification.where(byCategoryShortDesc(filter.getCategoryShortDesc()))
                .and(byUsername(filter.getUsername()));
    }

    static Specification<News> byCategoryShortDesc(String categoryShortDesc){
        return (root, query, cb) -> {
            if (!StringUtils.hasText(categoryShortDesc)) {
                return null;
            }

            return cb.equal(root.get(News.Fields.category).get(Category.Fields.shortDesc), categoryShortDesc);
        };
    }

    static Specification<News> byUsername(String username){
        if (!StringUtils.hasText(username)) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.get(News.Fields.user).get(User.Fields.username), username);
    }

}
