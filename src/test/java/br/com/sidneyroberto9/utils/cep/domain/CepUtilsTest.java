package br.com.sidneyroberto9.utils.cep.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CepUtilsTest {

    private CepUtils cepUtils;

    @BeforeEach
    void setUp() {
        cepUtils = new CepUtils();
    }

    @Test
    void normalize_strips_non_digits() {
        assertEquals("01310100", cepUtils.normalize("01310-100"));
        assertEquals("01310100", cepUtils.normalize("01310100"));
        assertEquals("01310100", cepUtils.normalize(" 01310-100 "));
    }

    @Test
    void normalize_returns_null_for_null_input() {
        assertNull(cepUtils.normalize(null));
    }

    @Test
    void isValid_accepts_8_digit_string() {
        assertTrue(cepUtils.isValid("01310100"));
    }

    @Test
    void isValid_rejects_wrong_length() {
        assertFalse(cepUtils.isValid("0131010"));
        assertFalse(cepUtils.isValid("013101000"));
        assertFalse(cepUtils.isValid(null));
    }

    @Test
    void isValid_rejects_non_digit() {
        assertFalse(cepUtils.isValid("0131010A"));
    }

    @Test
    void format_adds_hyphen() {
        assertEquals("01310-100", cepUtils.format("01310100"));
        assertEquals("01310-100", cepUtils.format("01310-100"));
    }

    @Test
    void format_throws_on_invalid_cep() {
        assertThrows(IllegalArgumentException.class, () -> cepUtils.format("123"));
        assertThrows(IllegalArgumentException.class, () -> cepUtils.format(null));
    }
}
