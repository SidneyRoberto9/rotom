package br.com.sidneyroberto9.utils.date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    private DateUtils dateUtils;

    @BeforeEach
    void setUp() {
        dateUtils = new DateUtils();
    }

    @Test
    void isWeekend_saturday_and_sunday() {
        assertTrue(dateUtils.isWeekend(LocalDate.of(2025, 1, 4)));  // saturday
        assertTrue(dateUtils.isWeekend(LocalDate.of(2025, 1, 5)));  // sunday
    }

    @Test
    void isWeekDay_monday_to_friday() {
        assertTrue(dateUtils.isWeekDay(LocalDate.of(2025, 1, 6)));  // monday
        assertTrue(dateUtils.isWeekDay(LocalDate.of(2025, 1, 10))); // friday
    }

    @Test
    void isWithinDateWindow_boundaries() {
        LocalDate date = LocalDate.of(2025, 1, 15);
        assertTrue(dateUtils.isWithinDateWindow(date, 10, 20));
        assertTrue(dateUtils.isWithinDateWindow(date, 15, 15));
        assertFalse(dateUtils.isWithinDateWindow(date, 1, 14));
        assertFalse(dateUtils.isWithinDateWindow(date, 16, 31));
    }

    @Test
    void formatDate_pattern() {
        assertEquals("05/06/2025", dateUtils.formatDate(LocalDate.of(2025, 6, 5)));
    }

    @Test
    void formatDateTime_pattern() {
        assertEquals("05/06/2025 14:30", dateUtils.formatDateTime(LocalDateTime.of(2025, 6, 5, 14, 30)));
    }

    @Test
    void formatMonthYear_zero_padded() {
        assertEquals("03/2025", dateUtils.formatMonthYear(3, 2025));
        assertEquals("11/2025", dateUtils.formatMonthYear(11, 2025));
    }

    @Test
    void toLocalDate_and_back_roundtrip() {
        LocalDate original = LocalDate.of(2025, 6, 15);
        Date date = dateUtils.toDate(original);
        assertEquals(original, dateUtils.toLocalDate(date));
    }

    @Test
    void isPast_and_isFuture() {
        assertTrue(dateUtils.isPast(LocalDate.of(2000, 1, 1)));
        assertTrue(dateUtils.isFuture(LocalDate.of(2099, 1, 1)));
    }
}
