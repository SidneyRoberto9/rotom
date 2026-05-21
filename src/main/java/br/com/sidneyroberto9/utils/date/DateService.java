package br.com.sidneyroberto9.utils.date;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

public class DateService {

    private final HolidayManager holidayManager;
    private final ZoneId zone;
    private final DateUtils dateUtils;

    public DateService() {
        this(HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BRAZIL)), ZoneId.systemDefault());
    }

    public DateService(HolidayManager holidayManager) {
        this(holidayManager, ZoneId.systemDefault());
    }

    public DateService(HolidayManager holidayManager, ZoneId zone) {
        this.holidayManager = holidayManager;
        this.zone = zone;
        this.dateUtils = new DateUtils();
    }

    // ── Holiday ──────────────────────────────────────────────────────────────

    public boolean isHoliday(LocalDate date) {
        return this.holidayManager.isHoliday(date);
    }

    public boolean isHoliday(LocalDateTime date) {
        return this.isHoliday(date.toLocalDate());
    }

    public boolean isHoliday(Date date) {
        return this.isHoliday(this.dateUtils.toLocalDate(date, zone));
    }

    // ── Business day check ───────────────────────────────────────────────────

    public boolean isBusinessDay(LocalDate date) {
        return this.dateUtils.isWeekDay(date) && !this.isHoliday(date);
    }

    public boolean isBusinessDay(LocalDateTime date) {
        return this.isBusinessDay(date.toLocalDate());
    }

    public boolean isBusinessDay(Date date) {
        return this.isBusinessDay(this.dateUtils.toLocalDate(date, zone));
    }

    // ── Add / subtract business days ─────────────────────────────────────────

    /**
     * Positive days = forward, negative = backward.
     */
    public LocalDate addBusinessDays(LocalDate date, int days) {
        if (days == 0) {

            return date;
        }

        int step = days > 0 ? 1 : -1;
        int remaining = Math.abs(days);

        LocalDate result = date;

        while (remaining > 0) {
            result = result.plusDays(step);

            if (this.isBusinessDay(result)) {
                remaining--;
            }
        }

        return result;
    }

    public LocalDateTime addBusinessDays(LocalDateTime date, int days) {
        return this.addBusinessDays(date.toLocalDate(), days).atTime(date.toLocalTime());
    }

    public Date addBusinessDays(Date date, int days) {
        return this.dateUtils.toDate(this.addBusinessDays(this.dateUtils.toLocalDate(date, zone), days), zone);
    }

    public LocalDate subtractBusinessDays(LocalDate date, int days) {
        if (days < 0) {
            throw new IllegalArgumentException("days deve ser maior ou igual a zero");
        }

        return this.addBusinessDays(date, -days);
    }

    public LocalDateTime subtractBusinessDays(LocalDateTime date, int days) {
        return this.subtractBusinessDays(date.toLocalDate(), days).atTime(date.toLocalTime());
    }

    public Date subtractBusinessDays(Date date, int days) {
        return this.dateUtils.toDate(this.subtractBusinessDays(this.dateUtils.toLocalDate(date, zone), days), zone);
    }

    // ── Adjust to business day ───────────────────────────────────────────────

    public LocalDate adjustToNextBusinessDay(LocalDate date) {
        LocalDate result = date;

        while (!this.isBusinessDay(result)) {
            result = result.plusDays(1);
        }

        return result;
    }

    public LocalDateTime adjustToNextBusinessDay(LocalDateTime date) {
        return this.adjustToNextBusinessDay(date.toLocalDate()).atTime(date.toLocalTime());
    }

    public Date adjustToNextBusinessDay(Date date) {
        return this.dateUtils.toDate(this.adjustToNextBusinessDay(this.dateUtils.toLocalDate(date, zone)), zone);
    }

    public LocalDate adjustToPreviousBusinessDay(LocalDate date) {
        LocalDate result = date;

        while (!this.isBusinessDay(result)) {
            result = result.minusDays(1);
        }

        return result;
    }

    public LocalDateTime adjustToPreviousBusinessDay(LocalDateTime date) {
        return this.adjustToPreviousBusinessDay(date.toLocalDate()).atTime(date.toLocalTime());
    }

    public Date adjustToPreviousBusinessDay(Date date) {
        return this.dateUtils.toDate(this.adjustToPreviousBusinessDay(this.dateUtils.toLocalDate(date, zone)), zone);
    }

    // ── Month boundaries ─────────────────────────────────────────────────────

    public LocalDate getFirstBusinessDayOfMonth(int year, int month) {
        return this.adjustToNextBusinessDay(YearMonth.of(year, month).atDay(1));
    }

    public LocalDate getLastBusinessDayOfMonth(int year, int month) {
        return this.adjustToPreviousBusinessDay(YearMonth.of(year, month).atEndOfMonth());
    }

    // ── Count ────────────────────────────────────────────────────────────────

    /**
     * Count business days between from (inclusive) and to (exclusive).
     */
    public long countBusinessDays(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("'to' deve ser igual ou posterior a 'from'");
        }

        long count = 0;
        LocalDate current = from;

        while (current.isBefore(to)) {
            if (this.isBusinessDay(current)) {
                count++;
            }

            current = current.plusDays(1);
        }

        return count;
    }
}
