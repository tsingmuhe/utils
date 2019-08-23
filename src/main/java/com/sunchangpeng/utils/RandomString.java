package com.sunchangpeng.utils;

import java.security.SecureRandom;

/**
 * Class that generates random strings.
 */
public class RandomString {
    private static final String EMPTY = "";
    public static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    protected static final char[] ALPHA_RANGE = new char[]{'A', 'Z', 'a', 'z'};
    protected static final char[] ALPHA_NUMERIC_RANGE = new char[]{'0', '9', 'A', 'Z', 'a', 'z'};

    protected final static RandomString INSTANCE = new RandomString();

    /**
     * Returns default instance of <code>RandomString</code>.
     */
    public static RandomString get() {
        return INSTANCE;
    }

    protected final SecureRandom rnd;

    /**
     * Creates new random string.
     */
    public RandomString() {
        this.rnd = new SecureRandom();
    }

    // ---------------------------------------------------------------- string

    /**
     * Creates random string whose length is the number of characters specified.
     * Characters are chosen from the set of characters specified.
     */
    public String random(int count, final char[] chars) {
        if (count == 0) {
            return EMPTY;
        }
        final char[] result = new char[count];
        while (count-- > 0) {
            result[count] = chars[rnd.nextInt(chars.length)];
        }
        return new String(result);
    }

    /**
     * Creates random string whose length is the number of characters specified.
     * Characters are chosen from the set of characters specified.
     */
    public String random(final int count, final String chars) {
        return random(count, chars.toCharArray());
    }

    // ---------------------------------------------------------------- range

    /**
     * Creates random string whose length is the number of characters specified.
     * Characters are chosen from the provided range.
     */
    public String random(int count, final char start, final char end) {
        if (count == 0) {
            return EMPTY;
        }
        final char[] result = new char[count];
        final int len = end - start + 1;
        while (count-- > 0) {
            result[count] = (char) (rnd.nextInt(len) + start);
        }
        return new String(result);
    }

    /**
     * Creates random string whose length is the number of characters specified.
     * Characters are chosen from the set of characters whose
     * ASCII value is between <code>32</code> and <code>126</code> (inclusive).
     */
    public String randomAscii(final int count) {
        return random(count, (char) 32, (char) 126);
    }

    /**
     * Creates random string that consist only of numbers.
     */
    public String randomNumeric(final int count) {
        return random(count, '0', '9');
    }

    /**
     * Creates random string whose length is the number of characters specified.
     * Characters are chosen from the multiple sets defined by range pairs.
     * All ranges must be in acceding order.
     */
    public String randomRanges(int count, final char... ranges) {
        if (count == 0) {
            return EMPTY;
        }
        int i = 0;
        int len = 0;
        final int[] lens = new int[ranges.length];
        while (i < ranges.length) {
            int gap = ranges[i + 1] - ranges[i] + 1;
            len += gap;
            lens[i] = len;
            i += 2;
        }

        final char[] result = new char[count];
        while (count-- > 0) {
            char c = 0;
            int r = rnd.nextInt(len);
            for (i = 0; i < ranges.length; i += 2) {
                if (r < lens[i]) {
                    r += ranges[i];
                    if (i != 0) {
                        r -= lens[i - 2];
                    }
                    c = (char) r;
                    break;
                }
            }
            result[count] = c;
        }
        return new String(result);
    }

    /**
     * Creates random string of characters.
     */
    public String randomAlpha(final int count) {
        return randomRanges(count, ALPHA_RANGE);
    }

    /**
     * Creates random string of characters and digits.
     */
    public String randomAlphaNumeric(final int count) {
        return randomRanges(count, ALPHA_NUMERIC_RANGE);
    }

    /**
     * Creates random string that contains only Base64 characters.
     */
    public String randomBase64(final int count) {
        return random(count, CHARS);
    }

}