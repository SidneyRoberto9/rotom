package br.io.sidneyroberto9.utils.date;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

/**
 * Service for business day calculations using the Brazilian national holiday calendar.
 * Uses the jollyday library for holiday identification.
 * All methods are overloaded for {@link LocalDate}, {@link LocalDateTime}, and {@link Date}.
 */
public class DateService {

    private final HolidayManager holidayManager;
    private final ZoneId zone;
    private final DateUtils dateUtils;

    /**
     * Creates the service with the Brazilian holiday calendar and the system default timezone.
     */
    public DateService() {
        this(HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BRAZIL)), ZoneId.systemDefault());
    }

    /**
     * Creates the service with a custom {@link HolidayManager} and the system default timezone.
     *
     * @param holidayManager holiday manager to use
     */
    public DateService(HolidayManager holidayManager) {
        this(holidayManager, ZoneId.systemDefault());
    }

    /**
     * Creates the service with a custom {@link HolidayManager} and timezone.
     *
     * @param holidayManager holiday manager to use
     * @param zone           timezone used when converting legacy {@link Date} instances
     */
    public DateService(HolidayManager holidayManager, ZoneId zone) {
        this.holidayManager = holidayManager;
        this.zone = zone;
        this.dateUtils = new DateUtils();
    }

    /**
     * Checks whether the given date is a Brazilian national holiday.
     *
     * @param date date to check
     * @return {@code true} if it is a holiday, {@code false} otherwise
     */
    public boolean isHoliday(LocalDate date) {
        return this.holidayManager.isHoliday(date);
    }

    /**
     * Checks whether the given date is a Brazilian national holiday.
     *
     * @param date date to check
     * @return {@code true} if it is a holiday, {@code false} otherwise
     */
    public boolean isHoliday(LocalDateTime date) {
        return this.isHoliday(date.toLocalDate());
    }

    /**
     * Checks whether the given date is a Brazilian national holiday.
     *
     * @param date date to check
     * @return {@code true} if it is a holiday, {@code false} otherwise
     */
    public boolean isHoliday(Date date) {
        return this.isHoliday(this.dateUtils.toLocalDate(date, zone));
    }

    /**
     * Checks whether the given date is a business day, meaning it is not a weekend and not a national holiday.
     *
     * @param date date to check
     * @return {@code true} if it is a business day, {@code false} otherwise
     */
    public boolean isBusinessDay(LocalDate date) {
        return this.dateUtils.isWeekDay(date) && !this.isHoliday(date);
    }

    /**
     * Checks whether the given date is a business day, meaning it is not a weekend and not a national holiday.
     *
     * @param date date to check
     * @return {@code true} if it is a business day, {@code false} otherwise
     */
    public boolean isBusinessDay(LocalDateTime date) {
        return this.isBusinessDay(date.toLocalDate());
    }

    /**
     * Checks whether the given date is a business day, meaning it is not a weekend and not a national holiday.
     *
     * @param date date to check
     * @return {@code true} if it is a business day, {@code false} otherwise
     */
    public boolean isBusinessDay(Date date) {
        return this.isBusinessDay(this.dateUtils.toLocalDate(date, zone));
    }

    /**
     * Adds or subtracts business days from the given date, skipping weekends and holidays.
     * Positive values move forward in time; negative values move backward.
     *
     * @param date reference date
     * @param days number of business days to add (positive) or subtract (negative)
     * @return new date after advancing or receding the business days
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

    /**
     * Adds or subtracts business days from the given date, skipping weekends and holidays.
     *
     * @param date reference date
     * @param days number of business days to add (positive) or subtract (negative)
     * @return new date preserving the original time
     */
    public LocalDateTime addBusinessDays(LocalDateTime date, int days) {
        return this.addBusinessDays(date.toLocalDate(), days).atTime(date.toLocalTime());
    }

    /**
     * Adds or subtracts business days from the given date, skipping weekends and holidays.
     *
     * @param date reference date
     * @param days number of business days to add (positive) or subtract (negative)
     * @return new date as a legacy {@link Date}
     */
    public Date addBusinessDays(Date date, int days) {
        return this.dateUtils.toDate(this.addBusinessDays(this.dateUtils.toLocalDate(date, zone), days), zone);
    }

    /**
     * Subtracts business days from the given date, skipping weekends and holidays.
     *
     * @param date reference date
     * @param days number of business days to subtract (must be zero or greater)
     * @return new date receded by the given business days
     * @throws IllegalArgumentException if {@code days} is negative
     */
    public LocalDate subtractBusinessDays(LocalDate date, int days) {
        if (days < 0) {
            throw new IllegalArgumentException("days deve ser maior ou igual a zero");
        }

        return this.addBusinessDays(date, -days);
    }

    /**
     * Subtracts business days from the given date, skipping weekends and holidays.
     *
     * @param date reference date
     * @param days number of business days to subtract (must be zero or greater)
     * @return new date preserving the original time
     * @throws IllegalArgumentException if {@code days} is negative
     */
    public LocalDateTime subtractBusinessDays(LocalDateTime date, int days) {
        return this.subtractBusinessDays(date.toLocalDate(), days).atTime(date.toLocalTime());
    }

    /**
     * Subtracts business days from the given date, skipping weekends and holidays.
     *
     * @param date reference date
     * @param days number of business days to subtract (must be zero or greater)
     * @return new date as a legacy {@link Date}
     * @throws IllegalArgumentException if {@code days} is negative
     */
    public Date subtractBusinessDays(Date date, int days) {
        return this.dateUtils.toDate(this.subtractBusinessDays(this.dateUtils.toLocalDate(date, zone), days), zone);
    }

    /**
     * Advances the date to the next business day if it is not already one.
     * If the date is already a business day, it is returned unchanged.
     *
     * @param date reference date
     * @return the date itself or the next business day
     */
    public LocalDate adjustToNextBusinessDay(LocalDate date) {
        LocalDate result = date;

        while (!this.isBusinessDay(result)) {
            result = result.plusDays(1);
        }

        return result;
    }

    /**
     * Advances the date to the next business day if it is not already one.
     *
     * @param date reference date
     * @return the date itself or the next business day, preserving the original time
     */
    public LocalDateTime adjustToNextBusinessDay(LocalDateTime date) {
        return this.adjustToNextBusinessDay(date.toLocalDate()).atTime(date.toLocalTime());
    }

    /**
     * Advances the date to the next business day if it is not already one.
     *
     * @param date reference date
     * @return the date itself or the next business day as a legacy {@link Date}
     */
    public Date adjustToNextBusinessDay(Date date) {
        return this.dateUtils.toDate(this.adjustToNextBusinessDay(this.dateUtils.toLocalDate(date, zone)), zone);
    }

    /**
     * Moves the date back to the previous business day if it is not already one.
     * If the date is already a business day, it is returned unchanged.
     *
     * @param date reference date
     * @return the date itself or the previous business day
     */
    public LocalDate adjustToPreviousBusinessDay(LocalDate date) {
        LocalDate result = date;

        while (!this.isBusinessDay(result)) {
            result = result.minusDays(1);
        }

        return result;
    }

    /**
     * Moves the date back to the previous business day if it is not already one.
     *
     * @param date reference date
     * @return the date itself or the previous business day, preserving the original time
     */
    public LocalDateTime adjustToPreviousBusinessDay(LocalDateTime date) {
        return this.adjustToPreviousBusinessDay(date.toLocalDate()).atTime(date.toLocalTime());
    }

    /**
     * Moves the date back to the previous business day if it is not already one.
     *
     * @param date reference date
     * @return the date itself or the previous business day as a legacy {@link Date}
     */
    public Date adjustToPreviousBusinessDay(Date date) {
        return this.dateUtils.toDate(this.adjustToPreviousBusinessDay(this.dateUtils.toLocalDate(date, zone)), zone);
    }

    /**
     * Returns the first business day of the given month.
     *
     * @param year  year (e.g. {@code 2025})
     * @param month month (1 = January, 12 = December)
     * @return first business day of the month
     */
    public LocalDate getFirstBusinessDayOfMonth(int year, int month) {
        return this.adjustToNextBusinessDay(YearMonth.of(year, month).atDay(1));
    }

    /**
     * Returns the last business day of the given month.
     *
     * @param year  year (e.g. {@code 2025})
     * @param month month (1 = January, 12 = December)
     * @return last business day of the month
     */
    public LocalDate getLastBusinessDayOfMonth(int year, int month) {
        return this.adjustToPreviousBusinessDay(YearMonth.of(year, month).atEndOfMonth());
    }

    /**
     * Counts the number of business days in the range from {@code from} (inclusive) to {@code to} (exclusive).
     *
     * @param from start date, inclusive
     * @param to   end date, exclusive
     * @return number of business days in the range
     * @throws IllegalArgumentException if {@code to} is before {@code from}
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
