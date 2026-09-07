package com.bookbrief.common;

import com.bookbrief.book.AuthorEntity;
import com.bookbrief.book.AuthorRepository;
import com.bookbrief.book.BookEntity;
import com.bookbrief.book.BookRepository;
import com.bookbrief.topic.ContentBlockEntity;
import com.bookbrief.topic.ContentBlockType;
import com.bookbrief.topic.TopicEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * TEMPORARY seed data so the API returns something meaningful during
 * Phase 1-3 testing, before the full seed catalog from Section 15
 * (Atomic Habits, Deep Work, Thinking Fast and Slow, Psychology of Money)
 * is built out properly. Only seeds if the books table is empty.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.count() > 0) {
            return; // already seeded
        }

        AuthorEntity author = new AuthorEntity();
        author.setName("James Clear");
        author.setBio("Author focused on habits, decision-making and continuous improvement.");
        authorRepository.save(author);

        BookEntity book = new BookEntity();
        book.setTitle("Atomic Habits");
        book.setAuthor(author);
        book.setCategory("Productivity");
        book.setDescription("An easy and proven way to build good habits and break bad ones.");
        book.setCoverUrl("https://example.com/covers/atomic-habits.jpg");
        book.setPublisher("Penguin Random House");
        book.setPublishedYear(2018);

        TopicEntity topic = new TopicEntity();
        topic.setBook(book);
        topic.setTopicNumber(1);
        topic.setTitle("The Power of 1%");
        topic.setSubtitle("Small changes, big results.");
        topic.setEstimatedReadMinutes(6);

        ContentBlockEntity quote = new ContentBlockEntity();
        quote.setTopic(topic);
        quote.setBlockOrder(1);
        quote.setType(ContentBlockType.QUOTE);
        quote.setContent("Improvement by 1% isn't particularly notable, but it can be far more meaningful in the long run.");

        ContentBlockEntity paragraph = new ContentBlockEntity();
        paragraph.setTopic(topic);
        paragraph.setBlockOrder(2);
        paragraph.setType(ContentBlockType.PARAGRAPH);
        paragraph.setContent("The core idea is simple: habits are the compound interest of self-improvement. "
                + "Getting 1 percent better every day counts for a lot in the long run.");

        ContentBlockEntity example = new ContentBlockEntity();
        example.setTopic(topic);
        example.setBlockOrder(3);
        example.setType(ContentBlockType.EXAMPLE);
        example.setTitle("Example");
        example.setContent("If you get 1% better each day for one year, you'll end up thirty-seven times better by the time you're done.");

        ContentBlockEntity takeaway = new ContentBlockEntity();
        takeaway.setTopic(topic);
        takeaway.setBlockOrder(4);
        takeaway.setType(ContentBlockType.KEY_TAKEAWAY);
        takeaway.setContent("Small habits repeated consistently compound into remarkable results over time.");

        topic.setBlocks(Arrays.asList(quote, paragraph, example, takeaway));
        book.setTopics(Arrays.asList(topic));

        bookRepository.save(book);
    }
}
