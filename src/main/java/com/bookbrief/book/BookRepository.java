package com.bookbrief.book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the books table (Section 6/7).
 * Query methods for discovery/search will be added in Phase 3
 * when BookController and BookService are built.
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
