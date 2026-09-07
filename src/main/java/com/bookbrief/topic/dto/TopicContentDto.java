package com.bookbrief.topic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TopicContentDto {
    private Long id;
    private Integer topicNumber;
    private String title;
    private String subtitle;
    private List<ContentBlockDto> blocks;
}
