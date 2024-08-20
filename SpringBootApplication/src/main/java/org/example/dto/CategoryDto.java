package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CategoryDto {
    private Integer id;
    private String name;
    private List<NewsDto> newsDto = List.of();
}
