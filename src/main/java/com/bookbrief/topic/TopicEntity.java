package com.bookbrief.topic;

import com.bookbrief.book.BookEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * topics table (Section 7).
 * One of the ~10-12 ordered major ideas belonging to a book.
 */
@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    @ToString.Exclude
    private BookEntity book;

    /** 1-based position of this topic within its book (e.g. 3 of 12). */
    @NotNull
    @Column(name = "topic_number", nullable = false)
    private Integer topicNumber;

    @NotNull
    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(name = "estimated_read_minutes")
    private Integer estimatedReadMinutes;

    /**
     * Ordered content blocks that make up this topic's reader-friendly document.
     * cascade + orphanRemoval so deleting/updating a topic's blocks stays consistent.
     */
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("blockOrder ASC")
    @JsonIgnore
    private List<ContentBlockEntity> blocks = new ArrayList<>();
}
