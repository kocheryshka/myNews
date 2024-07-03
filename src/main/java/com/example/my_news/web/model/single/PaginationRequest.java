package com.example.my_news.web.model.single;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {

    @NotNull
    @Positive
    private Integer pageSize;

    @NotNull
    @PositiveOrZero
    private Integer pageNumber;

    public PageRequest pageRequest(){
        return PageRequest.of(pageNumber, pageSize);
    }

}
