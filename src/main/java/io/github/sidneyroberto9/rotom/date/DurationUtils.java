package io.github.sidneyroberto9.rotom.date;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Utility class for computing and describing the duration between two points in time, with
 * Portuguese-language human-readable output.
 */
public class DurationUtils {

    /**
     * Computes the number of whole days between two date-times.
     *
     * @param start start of the interval
     * @param end   end of the interval
     * @return whole days between {@code start} and {@code end}
     */
    public long daysBetween(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toDays();
    }

    /**
     * Computes the number of whole hours between two date-times.
     *
     * @param start start of the interval
     * @param end   end of the interval
     * @return whole hours between {@code start} and {@code end}
     */
    public long hoursBetween(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toHours();
    }

    /**
     * Describes the duration between two date-times in hours and minutes, in Portuguese.
     * Example: {@code "2 hora(s) e 15 minuto(s)"}, or {@code "45 minutos"} when under an hour.
     *
     * @param start start of the interval
     * @param end   end of the interval
     * @return human-readable duration in hours and minutes
     */
    public String humanizeHoursAndMinutes(LocalDateTime start, LocalDateTime end) {
        long minutes = Duration.between(start, end).toMinutes();
        long hours = 0;

        if (minutes >= 60) {
            hours = minutes / 60;
            minutes = minutes % 60;
        }

        if (hours == 0) {
            return minutes + " minutos";
        }

        return hours + " hora(s) e " + minutes + " minuto(s)";
    }

    /**
     * Describes the duration between two date-times in days, hours, and minutes, in Portuguese.
     * Example: {@code "1 dia(s) 3 hora(s) e 20 minuto(s)"}.
     *
     * @param start start of the interval
     * @param end   end of the interval
     * @return human-readable duration in days, hours, and minutes
     */
    public String humanizeDaysHoursAndMinutes(LocalDateTime start, LocalDateTime end) {
        long minutes = Duration.between(start, end).toMinutes();
        long hours = 0;
        long days = 0;

        if (minutes >= 60) {
            hours = minutes / 60;
            minutes = minutes % 60;
        }

        if (hours >= 24) {
            days = hours / 24;
            hours = hours % 24;
        }

        if (days == 0) {
            if (hours == 0) {
                return minutes + " minutos";
            }

            return hours + " hora(s) e " + minutes + " minuto(s)";
        }

        return days + " dia(s) " + hours + " hora(s) e " + minutes + " minuto(s)";
    }
}
