package keno.net.rings_of_aeon.util;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {
    public static boolean isBetween(float inner, float outer, float subject) {
        return inner <= subject && subject <= outer;
    }

    public static int randomInt(int inner, int outer) {
        return ThreadLocalRandom.current().nextInt(inner, outer);
    }
}
