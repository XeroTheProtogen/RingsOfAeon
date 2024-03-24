package keno.net.rings_of_aeon.util;

public class MathUtils {
    public static boolean isBetween(float inner, float outer, float subject) {
        return inner <= subject && subject <= outer;
    }
}
