package com.mgil.courses.pluralsight.bookstore.util;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        return "13-771-" + Math.abs(new Random().nextInt());
    }
}
