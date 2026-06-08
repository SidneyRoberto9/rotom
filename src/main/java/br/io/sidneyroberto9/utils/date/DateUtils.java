package br.io.sidneyroberto9.utils.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Utility class for date conversions, temporal checks, and formatting.
 * All methods are overloaded for {@link LocalDate}, {@link LocalDateTime}, and {@link Date}.
 */
public class DateUtils {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Converts a {@link Date} to {@link LocalDate} using the system default timezone.
     *
     * @param date date to convert
     * @return date as {@link LocalDate}
     */
    public LocalDate toLocalDate(Date date) {
        return this.toLocalDate(date, ZoneId.systemDefault());
    }

    /**
     * Converts a {@link Date} to {@link LocalDate} using the given timezone.
     *
     * @param date date to convert
     * @param zone timezone to use for conversion
     * @return date as {@link LocalDate}
     */
    public LocalDate toLocalDate(Date date, ZoneId zone) {
        return date.toInstant().atZone(zone).toLocalDate();
    }

    /**
     * Converts a {@link Date} to {@link LocalDateTime} using the system default timezone.
     *
     * @param date date to convert
     * @return date and time as {@link LocalDateTime}
     */
    public LocalDateTime toLocalDateTime(Date date) {
        return this.toLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * Converts a {@link Date} to {@link LocalDateTime} using the given timezone.
     *
     * @param date date to convert
     * @param zone timezone to use for conversion
     * @return date and time as {@link LocalDateTime}
     */
    public LocalDateTime toLocalDateTime(Date date, ZoneId zone) {
        return date.toInstant().atZone(zone).toLocalDateTime();
    }

    /**
     * Converts a {@link LocalDate} to a legacy {@link Date} at the start of the day,
     * using the system default timezone.
     *
     * @param date date to convert
     * @return date as a legacy {@link Date}
     */
    public Date toDate(LocalDate date) {
        return this.toDate(date, ZoneId.systemDefault());
    }

    /**
     * Converts a {@link LocalDate} to a legacy {@link Date} at the start of the day,
     * using the given timezone.
     *
     * @param date date to convert
     * @param zone timezone to use for conversion
     * @return date as a legacy {@link Date}
     */
    public Date toDate(LocalDate date, ZoneId zone) {
        return Date.from(date.atStartOfDay(zone).toInstant());
    }

    /**
     * Converts a {@link LocalDateTime} to a legacy {@link Date} using the system default timezone.
     *
     * @param date date and time to convert
     * @return date as a legacy {@link Date}
     */
    public Date toDate(LocalDateTime date) {
        return this.toDate(date, ZoneId.systemDefault());
    }

    /**
     * Converts a {@link LocalDateTime} to a legacy {@link Date} using the given timezone.
     *
     * @param date date and time to convert
     * @param zone timezone to use for conversion
     * @return date as a legacy {@link Date}
     */
    public Date toDate(LocalDateTime date, ZoneId zone) {
        return Date.from(date.atZone(zone).toInstant());
    }

    /**
     * Checks whether the date falls on a weekend (Saturday or Sunday).
     *
     * @param date date to check
     * @return {@code true} if Saturday or Sunday, {@code false} otherwise
     */
    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    /**
     * Checks whether the date falls on a weekend (Saturday or Sunday).
     *
     * @param date date to check
     * @return {@code true} if Saturday or Sunday, {@code false} otherwise
     */
    public boolean isWeekend(LocalDateTime date) {
        return this.isWeekend(date.toLocalDate());
    }

    /**
     * Checks whether the date falls on a weekend (Saturday or Sunday).
     *
     * @param date date to check
     * @return {@code true} if Saturday or Sunday, {@code false} otherwise
     */
    public boolean isWeekend(Date date) {
        return this.isWeekend(this.toLocalDate(date));
    }

    /**
     * Checks whether the date falls on a weekday (Monday through Friday).
     *
     * @param date date to check
     * @return {@code true} if a weekday, {@code false} otherwise
     */
    public boolean isWeekDay(LocalDate date) {
        return !this.isWeekend(date);
    }

    /**
     * Checks whether the date falls on a weekday (Monday through Friday).
     *
     * @param date date to check
     * @return {@code true} if a weekday, {@code false} otherwise
     */
    public boolean isWeekDay(LocalDateTime date) {
        return !this.isWeekend(date);
    }

    /**
     * Checks whether the date falls on a weekday (Monday through Friday).
     *
     * @param date date to check
     * @return {@code true} if a weekday, {@code false} otherwise
     */
    public boolean isWeekDay(Date date) {
        return !this.isWeekend(date);
    }

    /**
     * Checks whether the date corresponds to today.
     *
     * @param date date to check
     * @return {@code true} if today, {@code false} otherwise
     */
    public boolean isToday(LocalDate date) {
        return LocalDate.now().equals(date);
    }

    /**
     * Checks whether the date corresponds to today.
     *
     * @param date date to check
     * @return {@code true} if today, {@code false} otherwise
     */
    public boolean isToday(LocalDateTime date) {
        return this.isToday(date.toLocalDate());
    }

    /**
     * Checks whether the date corresponds to today.
     *
     * @param date date to check
     * @return {@code true} if today, {@code false} otherwise
     */
    public boolean isToday(Date date) {
        return this.isToday(this.toLocalDate(date));
    }

    /**
     * Checks whether the date is before today.
     *
     * @param date date to check
     * @return {@code true} if in the past, {@code false} otherwise
     */
    public boolean isPast(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    /**
     * Checks whether the date and time is before the current moment.
     *
     * @param date date and time to check
     * @return {@code true} if in the past, {@code false} otherwise
     */
    public boolean isPast(LocalDateTime date) {
        return date.isBefore(LocalDateTime.now());
    }

    /**
     * Checks whether the date is after today.
     *
     * @param date date to check
     * @return {@code true} if in the future, {@code false} otherwise
     */
    public boolean isFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    /**
     * Checks whether the date and time is after the current moment.
     *
     * @param date date and time to check
     * @return {@code true} if in the future, {@code false} otherwise
     */
    public boolean isFuture(LocalDateTime date) {
        return date.isAfter(LocalDateTime.now());
    }

    /**
     * Checks whether the day of the month falls within the window defined by {@code startDay} and {@code endDay}, inclusive.
     *
     * @param date     date to check
     * @param startDay start day of the window (1–31)
     * @param endDay   end day of the window (1–31)
     * @return {@code true} if the day of the month is within the window, {@code false} otherwise
     */
    public boolean isWithinDateWindow(LocalDate date, int startDay, int endDay) {
        int day = date.getDayOfMonth();
        return day >= startDay && day <= endDay;
    }

    /**
     * Checks whether the day of the month falls within the window defined by {@code startDay} and {@code endDay}, inclusive.
     *
     * @param date     date to check
     * @param startDay start day of the window (1–31)
     * @param endDay   end day of the window (1–31)
     * @return {@code true} if the day of the month is within the window, {@code false} otherwise
     */
    public boolean isWithinDateWindow(LocalDateTime date, int startDay, int endDay) {
        return this.isWithinDateWindow(date.toLocalDate(), startDay, endDay);
    }

    /**
     * Checks whether the day of the month falls within the window defined by {@code startDay} and {@code endDay}, inclusive.
     *
     * @param date     date to check
     * @param startDay start day of the window (1–31)
     * @param endDay   end day of the window (1–31)
     * @return {@code true} if the day of the month is within the window, {@code false} otherwise
     */
    public boolean isWithinDateWindow(Date date, int startDay, int endDay) {
        return this.isWithinDateWindow(this.toLocalDate(date), startDay, endDay);
    }

    /**
     * Formats the date as {@code dd/MM/yyyy}.
     *
     * @param date date to format
     * @return date formatted as {@code dd/MM/yyyy}
     */
    public String formatDate(LocalDate date) {
        return date.format(DATE_FMT);
    }

    /**
     * Formats the date as {@code dd/MM/yyyy} (time part is ignored).
     *
     * @param date date and time to format
     * @return date formatted as {@code dd/MM/yyyy}
     */
    public String formatDate(LocalDateTime date) {
        return date.format(DATE_FMT);
    }

    /**
     * Formats the date as {@code dd/MM/yyyy}.
     *
     * @param date date to format
     * @return date formatted as {@code dd/MM/yyyy}
     */
    public String formatDate(Date date) {
        return this.formatDate(this.toLocalDate(date));
    }

    /**
     * Formats the date and time as {@code dd/MM/yyyy HH:mm}.
     *
     * @param date date and time to format
     * @return date and time formatted as {@code dd/MM/yyyy HH:mm}
     */
    public String formatDateTime(LocalDateTime date) {
        return date.format(DATETIME_FMT);
    }

    /**
     * Formats the date and time as {@code dd/MM/yyyy HH:mm}.
     *
     * @param date date to format
     * @return date and time formatted as {@code dd/MM/yyyy HH:mm}
     */
    public String formatDateTime(Date date) {
        return this.formatDateTime(this.toLocalDateTime(date));
    }

    /**
     * Formats the month and year as {@code MM/yyyy}.
     *
     * @param month month (1 = January, 12 = December)
     * @param year  year (e.g. {@code 2025})
     * @return month and year formatted as {@code MM/yyyy}
     */
    public String formatMonthYear(int month, int year) {
        return String.format("%02d/%d", month, year);
    }
}
