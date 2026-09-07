package com.bookbrief.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDetailDto {
    private Long id;
    private String title;
    private String authorName;
    private String authorBio;
    private String coverUrl;
    private String category;
    private String description;
    private String publisher;
    private Integer publishedYear;
    private int topicCount;
}
