package com.bookbrief.topic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TopicSummaryDto {
    private Long id;
    private Integer topicNumber;
    private String title;
    private String subtitle;
    private Integer estimatedReadMinutes;
}
