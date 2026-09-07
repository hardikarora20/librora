package com.bookbrief.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookSummaryDto {
    private Long id;
    private String title;
    private String authorName;
    private String coverUrl;
    private String category;
    private int topicCount;
}
