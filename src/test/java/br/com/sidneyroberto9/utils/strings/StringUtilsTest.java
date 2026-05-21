package br.com.sidneyroberto9.utils.strings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    private StringUtils stringUtils;

    @BeforeEach
    void setUp() {
        stringUtils = new StringUtils();
    }

    // ── isBlank / isNotBlank ──────────────────────────────────────────────────

    @Test
    void isBlank_null_and_empty() {
        assertTrue(stringUtils.isBlank(null));
        assertTrue(stringUtils.isBlank(""));
        assertTrue(stringUtils.isBlank("   "));
    }

    @Test
    void isNotBlank_valid_string() {
        assertTrue(stringUtils.isNotBlank("hello"));
        assertFalse(stringUtils.isNotBlank(null));
    }

    // ── trimOrNull / requireNonBlank ──────────────────────────────────────────

    @Test
    void trimOrNull_blank_returns_null() {
        assertNull(stringUtils.trimOrNull(null));
        assertNull(stringUtils.trimOrNull("   "));
    }

    @Test
    void trimOrNull_trims_value() {
        assertEquals("hello", stringUtils.trimOrNull("  hello  "));
    }

    @Test
    void requireNonBlank_throws_on_blank() {
        assertThrows(IllegalArgumentException.class,
                () -> stringUtils.requireNonBlank(null, "campo obrigatório"));
        assertThrows(IllegalArgumentException.class,
                () -> stringUtils.requireNonBlank("  ", "campo obrigatório"));
    }

    @Test
    void requireNonBlank_returns_trimmed() {
        assertEquals("ok", stringUtils.requireNonBlank("  ok  ", "erro"));
    }

    // ── capitalize / capitalizeWords ──────────────────────────────────────────

    @Test
    void capitalize_first_upper_rest_lower() {
        assertEquals("Hello", stringUtils.capitalize("hello"));
        assertEquals("Hello", stringUtils.capitalize("HELLO"));
    }

    @Test
    void capitalizeWords_lowercases_connectives() {
        assertEquals("João de Oliveira", stringUtils.capitalizeWords("joão de oliveira"));
        assertEquals("Maria das Graças", stringUtils.capitalizeWords("maria das graças"));
        assertEquals("José do Carmo", stringUtils.capitalizeWords("josé do carmo"));
    }

    @Test
    void capitalizeWords_first_word_is_always_capitalized() {
        assertEquals("De Oliveira", stringUtils.capitalizeWords("de oliveira"));
    }

    @Test
    void capitalizeWords_null_returns_empty() {
        assertEquals("", stringUtils.capitalizeWords(null));
    }

    // ── firstTwoNames ─────────────────────────────────────────────────────────

    @Test
    void firstTwoNames_returns_first_two() {
        assertEquals("João Silva", stringUtils.firstTwoNames("João Silva dos Santos"));
    }

    @Test
    void firstTwoNames_single_name() {
        assertEquals("João", stringUtils.firstTwoNames("João"));
    }

    // ── digitsOnly ────────────────────────────────────────────────────────────

    @Test
    void digitsOnly_removes_non_digits() {
        assertEquals("01310100", stringUtils.digitsOnly("01310-100"));
        assertEquals("12345678901", stringUtils.digitsOnly("123.456.789-01"));
    }

    // ── removeAccents / slugify ───────────────────────────────────────────────

    @Test
    void removeAccents_strips_diacritics() {
        assertEquals("sao paulo", stringUtils.removeAccents("são paulo"));
        assertEquals("acoes", stringUtils.removeAccents("ações"));
    }

    @Test
    void slugify_creates_url_slug() {
        assertEquals("sao-paulo", stringUtils.slugify("São Paulo"));
        assertEquals("hello-world", stringUtils.slugify("  Hello   World  "));
        assertEquals("joao-da-silva", stringUtils.slugify("João da Silva"));
    }

    // ── truncate ──────────────────────────────────────────────────────────────

    @Test
    void truncate_cuts_at_max() {
        assertEquals("hel", stringUtils.truncate("hello", 3));
        assertEquals("hello", stringUtils.truncate("hello", 10));
    }

    @Test
    void truncate_negative_max_throws() {
        assertThrows(IllegalArgumentException.class, () -> stringUtils.truncate("hi", -1));
    }

    // ── containsIgnoreCase ────────────────────────────────────────────────────

    @Test
    void containsIgnoreCase_matches_regardless_of_case() {
        assertTrue(stringUtils.containsIgnoreCase("Hello World", "WORLD"));
        assertFalse(stringUtils.containsIgnoreCase("Hello", "xyz"));
        assertFalse(stringUtils.containsIgnoreCase(null, "x"));
    }

    // ── email ─────────────────────────────────────────────────────────────────

    @Test
    void normalizeEmail_trims_and_lowercases() {
        assertEquals("user@example.com", stringUtils.normalizeEmail("  User@Example.COM  "));
    }

    @Test
    void emailDomain_extracts_domain_name() {
        assertEquals("GMAIL", stringUtils.emailDomain("user@gmail.com"));
    }

    @Test
    void emailDomain_throws_on_invalid() {
        assertThrows(IllegalArgumentException.class, () -> stringUtils.emailDomain("notanemail"));
        assertThrows(IllegalArgumentException.class, () -> stringUtils.emailDomain(null));
    }

    @Test
    void maskEmail_masks_local_part() {
        assertEquals("u***@gmail.com", stringUtils.maskEmail("user@gmail.com"));
    }
}
