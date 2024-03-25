package keno.net.rings_of_aeon.util;

public class TimeConversion {
    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static int minutesToTicks(int minutes) {
        return secondsToTicks(minutes * 60);
    }
}
