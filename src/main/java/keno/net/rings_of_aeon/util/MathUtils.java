package keno.net.rings_of_aeon.util;

public class MathUtils {
    public static boolean isBetween(float inner, float outer, float subject) {
        return inner <= subject && subject <= outer;
    }

    public static float clamp(float min, float input, float max) {
        if (input <= min) {
            return min;
        } else if (input >= max) {
            return max;
        }
        return input;
    }
}
