package com.sunchangpeng.utils.collection;

import org.junit.Test;

public class ArrayIteratorTest {
    @Test
    public void test() {
        ArrayIterator<String> iterator = new ArrayIterator<>(new String[]{"s", "u", "n"});
        iterator.forEachRemaining(item -> System.out.println(item));
    }
}