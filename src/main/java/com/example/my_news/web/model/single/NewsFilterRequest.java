package com.example.my_news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsFilterRequest {

    private PaginationRequest pagination;

    private String categoryShortDesc;

    private String username;

}
