package com.bookbrief.topic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    List<TopicEntity> findByBookIdOrderByTopicNumberAsc(Long bookId);

    Optional<TopicEntity> findByBookIdAndTopicNumber(Long bookId, Integer topicNumber);
}
