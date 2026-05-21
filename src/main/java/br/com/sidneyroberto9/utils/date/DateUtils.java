package br.com.sidneyroberto9.utils.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ── Conversions ──────────────────────────────────────────────────────────

    public LocalDate toLocalDate(Date date) {
        return this.toLocalDate(date, ZoneId.systemDefault());
    }

    public LocalDate toLocalDate(Date date, ZoneId zone) {
        return date.toInstant().atZone(zone).toLocalDate();
    }

    public LocalDateTime toLocalDateTime(Date date) {
        return this.toLocalDateTime(date, ZoneId.systemDefault());
    }

    public LocalDateTime toLocalDateTime(Date date, ZoneId zone) {
        return date.toInstant().atZone(zone).toLocalDateTime();
    }

    public Date toDate(LocalDate date) {
        return this.toDate(date, ZoneId.systemDefault());
    }

    public Date toDate(LocalDate date, ZoneId zone) {
        return Date.from(date.atStartOfDay(zone).toInstant());
    }

    public Date toDate(LocalDateTime date) {
        return this.toDate(date, ZoneId.systemDefault());
    }

    public Date toDate(LocalDateTime date, ZoneId zone) {
        return Date.from(date.atZone(zone).toInstant());
    }

    // ── Week / weekend ───────────────────────────────────────────────────────

    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public boolean isWeekend(LocalDateTime date) {
        return this.isWeekend(date.toLocalDate());
    }

    public boolean isWeekend(Date date) {
        return this.isWeekend(this.toLocalDate(date));
    }

    public boolean isWeekDay(LocalDate date) {
        return !this.isWeekend(date);
    }

    public boolean isWeekDay(LocalDateTime date) {
        return !this.isWeekend(date);
    }

    public boolean isWeekDay(Date date) {
        return !this.isWeekend(date);
    }

    // ── Relative checks ──────────────────────────────────────────────────────

    public boolean isToday(LocalDate date) {
        return LocalDate.now().equals(date);
    }

    public boolean isToday(LocalDateTime date) {
        return this.isToday(date.toLocalDate());
    }

    public boolean isToday(Date date) {
        return this.isToday(this.toLocalDate(date));
    }

    public boolean isPast(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    public boolean isPast(LocalDateTime date) {
        return date.isBefore(LocalDateTime.now());
    }

    public boolean isFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    public boolean isFuture(LocalDateTime date) {
        return date.isAfter(LocalDateTime.now());
    }

    // ── Date window ──────────────────────────────────────────────────────────

    public boolean isWithinDateWindow(LocalDate date, int startDay, int endDay) {
        int day = date.getDayOfMonth();
        return day >= startDay && day <= endDay;
    }

    public boolean isWithinDateWindow(LocalDateTime date, int startDay, int endDay) {
        return this.isWithinDateWindow(date.toLocalDate(), startDay, endDay);
    }

    public boolean isWithinDateWindow(Date date, int startDay, int endDay) {
        return this.isWithinDateWindow(this.toLocalDate(date), startDay, endDay);
    }

    // ── Formatting ───────────────────────────────────────────────────────────

    public String formatDate(LocalDate date) {
        return date.format(DATE_FMT);
    }

    public String formatDate(LocalDateTime date) {
        return date.format(DATE_FMT);
    }

    public String formatDate(Date date) {
        return this.formatDate(this.toLocalDate(date));
    }

    public String formatDateTime(LocalDateTime date) {
        return date.format(DATETIME_FMT);
    }

    public String formatDateTime(Date date) {
        return this.formatDateTime(this.toLocalDateTime(date));
    }

    public String formatMonthYear(int month, int year) {
        return String.format("%02d/%d", month, year);
    }
}
