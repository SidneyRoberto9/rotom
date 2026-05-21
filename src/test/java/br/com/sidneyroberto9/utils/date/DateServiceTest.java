package br.com.sidneyroberto9.utils.date;

import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    private DateService service;

    @BeforeEach
    void setUp() {
        HolidayManager manager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.BRAZIL));
        service = new DateService(manager);
    }

    @Test
    void isHoliday_christmas() {
        assertTrue(service.isHoliday(LocalDate.of(2025, 12, 25)));
    }

    @Test
    void isHoliday_normal_day() {
        assertFalse(service.isHoliday(LocalDate.of(2025, 6, 10)));
    }

    @Test
    void isBusinessDay_weekday_non_holiday() {
        assertTrue(service.isBusinessDay(LocalDate.of(2025, 6, 10))); // tuesday
    }

    @Test
    void isBusinessDay_saturday() {
        assertFalse(service.isBusinessDay(LocalDate.of(2025, 6, 7))); // saturday
    }

    @Test
    void isBusinessDay_holiday() {
        assertFalse(service.isBusinessDay(LocalDate.of(2025, 12, 25)));
    }

    @Test
    void addBusinessDays_forward() {
        LocalDate start = LocalDate.of(2025, 6, 9); // monday
        LocalDate result = service.addBusinessDays(start, 5);
        assertTrue(result.isAfter(start));
        assertTrue(service.isBusinessDay(result));
    }

    @Test
    void addBusinessDays_negative_goes_backward() {
        LocalDate start = LocalDate.of(2025, 6, 16); // monday
        LocalDate result = service.addBusinessDays(start, -5);
        assertTrue(result.isBefore(start));
        assertTrue(service.isBusinessDay(result));
    }

    @Test
    void addBusinessDays_zero_returns_same() {
        LocalDate start = LocalDate.of(2025, 6, 9);
        assertEquals(start, service.addBusinessDays(start, 0));
    }

    @Test
    void subtractBusinessDays_negative_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.subtractBusinessDays(LocalDate.now(), -1));
    }

    @Test
    void adjustToNextBusinessDay_skips_weekend() {
        LocalDate saturday = LocalDate.of(2025, 6, 7);
        LocalDate result = service.adjustToNextBusinessDay(saturday);
        assertEquals(DayOfWeek.MONDAY, result.getDayOfWeek());
    }

    @Test
    void adjustToPreviousBusinessDay_skips_weekend() {
        LocalDate sunday = LocalDate.of(2025, 6, 8);
        LocalDate result = service.adjustToPreviousBusinessDay(sunday);
        assertEquals(DayOfWeek.FRIDAY, result.getDayOfWeek());
    }

    @Test
    void getFirstBusinessDayOfMonth_january_2025() {
        LocalDate first = service.getFirstBusinessDayOfMonth(2025, 1);
        assertTrue(service.isBusinessDay(first));
        assertEquals(1, first.getMonthValue());
    }

    @Test
    void getLastBusinessDayOfMonth_january_2025() {
        LocalDate last = service.getLastBusinessDayOfMonth(2025, 1);
        assertTrue(service.isBusinessDay(last));
        assertEquals(1, last.getMonthValue());
    }

    @Test
    void countBusinessDays_one_week() {
        LocalDate from = LocalDate.of(2025, 6, 9);  // monday
        LocalDate to = LocalDate.of(2025, 6, 14);   // saturday (exclusive)
        assertEquals(5, service.countBusinessDays(from, to));
    }

    @Test
    void countBusinessDays_to_before_from_throws() {
        LocalDate from = LocalDate.of(2025, 6, 10);
        LocalDate to = LocalDate.of(2025, 6, 9);
        assertThrows(IllegalArgumentException.class, () -> service.countBusinessDays(from, to));
    }
}
