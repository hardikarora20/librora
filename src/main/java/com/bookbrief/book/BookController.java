package com.bookbrief.book;

import com.bookbrief.book.dto.BookDetailDto;
import com.bookbrief.book.dto.BookSummaryDto;
import com.bookbrief.book.dto.HomeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API Blueprint (Section 9) - book discovery endpoints.
 * GET /api/home
 * GET /api/books
 * GET /api/books/{id}
 * All public (Section 10) — no auth required.
 */
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/home")
    public HomeResponseDto getHome() {
        return bookService.getHome();
    }

    @GetMapping("/api/books")
    public List<BookSummaryDto> getBooks(@RequestParam(required = false) String search) {
        return bookService.getAllBooks(search);
    }

    @GetMapping("/api/books/{id}")
    public BookDetailDto getBookDetail(@PathVariable Long id) {
        return bookService.getBookDetail(id);
    }
}
