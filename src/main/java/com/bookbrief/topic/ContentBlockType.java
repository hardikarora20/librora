package com.bookbrief.topic;

/**
 * Types of structured content blocks that compose a topic (Section 8).
 * Keeping this as an enum lets the frontend render each block differently
 * (e.g. QUOTE gets editorial styling, EXAMPLE gets a card) without the
 * database storing an entire topic as one unstructured text field.
 */
public enum ContentBlockType {
    PARAGRAPH,
    QUOTE,
    EXAMPLE,
    KEY_TAKEAWAY,
    CALLOUT
}
