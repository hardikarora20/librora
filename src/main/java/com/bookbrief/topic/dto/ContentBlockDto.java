package com.bookbrief.topic.dto;

import com.bookbrief.topic.ContentBlockType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentBlockDto {
    private ContentBlockType type;
    private String title;
    private String content;
}
