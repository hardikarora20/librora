package com.bookbrief.topic;

import com.bookbrief.topic.dto.TopicContentDto;
import com.bookbrief.topic.dto.TopicSummaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API Blueprint (Section 9) - topic list + reader content endpoints.
 * GET /api/books/{id}/topics
 * GET /api/books/{id}/topics/{number}
 * Public book catalog endpoints per Section 10.
 */
@RestController
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/api/books/{id}/topics")
    public List<TopicSummaryDto> getTopics(@PathVariable Long id) {
        return topicService.getTopicsForBook(id);
    }

    @GetMapping("/api/books/{id}/topics/{number}")
    public TopicContentDto getTopicContent(@PathVariable Long id, @PathVariable Integer number) {
        return topicService.getTopicContent(id, number);
    }
}
