package wooter.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class MyTimer {

    public static void getDurationToMillis(LocalDateTime start, LocalDateTime end){
        long duration = Duration.between(start, end).toMillis();
        System.out.println(duration + " ms");
    }

    public static void getDurationToMillis(LocalDateTime start){
        getDurationToMillis(start, LocalDateTime.now());
    }
}
