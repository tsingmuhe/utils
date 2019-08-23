package com.sunchangpeng.utils;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {

    /**
     * Generates pseudo-random long from specific range. Generated number is
     * great or equals to min parameter value and less then max parameter value.
     *
     * @param min lower (inclusive) boundary
     * @param max higher (exclusive) boundary
     * @return pseudo-random value
     */

    public static long randomLong(final long min, final long max) {
        return min + (long) (ThreadLocalRandom.current().nextDouble() * (max - min));
    }


    /**
     * Generates pseudo-random integer from specific range. Generated number is
     * great or equals to min parameter value and less then max parameter value.
     *
     * @param min lower (inclusive) boundary
     * @param max higher (exclusive) boundary
     * @return pseudo-random value
     */
    public static int randomInt(final int min, final int max) {
        return min + (int) (ThreadLocalRandom.current().nextDouble() * (max - min));
    }
}
