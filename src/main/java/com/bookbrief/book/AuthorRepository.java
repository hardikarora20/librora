package com.bookbrief.book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Not explicitly named in the blueprint's package list, but needed in practice:
 * authors are persisted independently before being linked to a book via author_id.
 */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
