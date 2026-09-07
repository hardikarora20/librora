package com.bookbrief.book;

import com.bookbrief.topic.TopicEntity;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * books table (Section 7).
 * Book metadata, cover URL, description and publishing information.
 */
@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private AuthorEntity author;

    /**
     * Simple category label for now (e.g. "Productivity", "Psychology").
     * Note: blueprint's domain diagram shows a Category relationship, but the
     * core tables list (Section 7) doesn't define a separate categories table.
     * Promote to its own CategoryEntity + many-to-many later if you need
     * multi-category tagging or category-level browsing pages.
     */
    private String category;

    @Lob
    @Column(length = 4000)
    private String description;

    @Column(name = "cover_url")
    private String coverUrl;

    private String publisher;

    @Column(name = "published_year")
    private Integer publishedYear;

    /**
     * Ordered topics (1..12) belonging to this book.
     * cascade + orphanRemoval so removing a book cleans up its topics.
     */
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("topicNumber ASC")
    @JsonIgnore
    private List<TopicEntity> topics = new ArrayList<>();
}
