package com.bookbrief.topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * content_blocks table (Section 7/8).
 * A single ordered piece of a topic's structured content
 * (paragraph, quote, example, key takeaway or callout).
 */
@Entity
@Table(name = "content_blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentBlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    @ToString.Exclude
    private TopicEntity topic;

    /** Zero-based (or one-based, be consistent) position of this block within its topic. */
    @NotNull
    @Column(name = "block_order", nullable = false)
    private Integer blockOrder;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ContentBlockType type;

    /** Optional heading, mainly used for EXAMPLE / CALLOUT blocks. */
    private String title;

    @Lob
    @NotNull
    @Column(nullable = false)
    private String content;
}
