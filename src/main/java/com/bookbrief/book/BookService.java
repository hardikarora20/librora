package com.bookbrief.book;

import com.bookbrief.book.dto.BookDetailDto;
import com.bookbrief.book.dto.BookSummaryDto;
import com.bookbrief.book.dto.HomeResponseDto;
import com.bookbrief.common.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic for book discovery and detail (Section 9).
 * Controllers stay thin; this is where DTO mapping and rules live.
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /** GET /api/books — browse/search. For now returns all books; search filtering added when needed. */
    public List<BookSummaryDto> getAllBooks(String search) {
        return bookRepository.findAll().stream()
                .filter(book -> search == null || search.isEmpty()
                        || book.getTitle().toLowerCase().contains(search.toLowerCase()))
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    /** GET /api/books/{id} — book detail and metadata. */
    public BookDetailDto getBookDetail(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return toDetailDto(book);
    }

    /**
     * GET /api/home — featured + recommended collections.
     * Simple placeholder logic for now: featured = most recently added few,
     * recommended = everything else. Replace with real ranking later.
     */
    public HomeResponseDto getHome() {
        List<BookEntity> all = bookRepository.findAll();

        List<BookSummaryDto> featured = all.stream()
                .sorted(Comparator.comparing(BookEntity::getId).reversed())
                .limit(5)
                .map(this::toSummaryDto)
                .collect(Collectors.toList());

        List<BookSummaryDto> recommended = all.stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());

        return new HomeResponseDto(featured, recommended);
    }

    private BookSummaryDto toSummaryDto(BookEntity book) {
        return new BookSummaryDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor() != null ? book.getAuthor().getName() : null,
                book.getCoverUrl(),
                book.getCategory(),
                book.getTopics().size()
        );
    }

    private BookDetailDto toDetailDto(BookEntity book) {
        return new BookDetailDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor() != null ? book.getAuthor().getName() : null,
                book.getAuthor() != null ? book.getAuthor().getBio() : null,
                book.getCoverUrl(),
                book.getCategory(),
                book.getDescription(),
                book.getPublisher(),
                book.getPublishedYear(),
                book.getTopics().size()
        );
    }
}
