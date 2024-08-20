package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class NewsDto {
    private Integer id;
    private String title;
    private String text;
    private String categoryName;
    private LocalDateTime date;
}
