package com.bookbrief.topic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the topics table (Section 6/7).
 * Includes the two lookups the reader flow needs most:
 * ordered topic list for a book, and a single topic by its number within the book.
 */
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    List<TopicEntity> findByBookIdOrderByTopicNumberAsc(Long bookId);

    Optional<TopicEntity> findByBookIdAndTopicNumber(Long bookId, Integer topicNumber);
}
