package com.mgil.courses.pluralsight.bookstore.util;

public class TextUtil {

    /**
     * Replace one of more spaces for only one
     * @param text text to sanitize
     * @return a text with only one space
     */
    public String sanitize(String text) {
        return text.replaceAll("\\s+"," ");
    }
}
