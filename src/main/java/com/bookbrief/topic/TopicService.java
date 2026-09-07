package com.bookbrief.topic;

import com.bookbrief.common.NotFoundException;
import com.bookbrief.topic.dto.ContentBlockDto;
import com.bookbrief.topic.dto.TopicContentDto;
import com.bookbrief.topic.dto.TopicSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic for the topic list and reader content (Sections 8 & 9).
 */
@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    /** GET /api/books/{id}/topics — ordered topic list. */
    public List<TopicSummaryDto> getTopicsForBook(Long bookId) {
        return topicRepository.findByBookIdOrderByTopicNumberAsc(bookId).stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    /** GET /api/books/{id}/topics/{number} — full reader-friendly topic document. */
    public TopicContentDto getTopicContent(Long bookId, Integer topicNumber) {
        TopicEntity topic = topicRepository.findByBookIdAndTopicNumber(bookId, topicNumber)
                .orElseThrow(() -> new NotFoundException("Topic not found"));

        List<ContentBlockDto> blocks = topic.getBlocks().stream()
                .map(b -> new ContentBlockDto(b.getType(), b.getTitle(), b.getContent()))
                .collect(Collectors.toList());

        return new TopicContentDto(
                topic.getId(),
                topic.getTopicNumber(),
                topic.getTitle(),
                topic.getSubtitle(),
                blocks
        );
    }

    private TopicSummaryDto toSummaryDto(TopicEntity topic) {
        return new TopicSummaryDto(
                topic.getId(),
                topic.getTopicNumber(),
                topic.getTitle(),
                topic.getSubtitle(),
                topic.getEstimatedReadMinutes()
        );
    }
}
