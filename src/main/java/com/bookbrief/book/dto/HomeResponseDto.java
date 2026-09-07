package com.bookbrief.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeResponseDto {
    private List<BookSummaryDto> featuredBooks;
    private List<BookSummaryDto> recommendedBooks;
}
