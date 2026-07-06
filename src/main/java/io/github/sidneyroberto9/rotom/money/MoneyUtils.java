package io.github.sidneyroberto9.rotom.money;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utility class for formatting monetary values as Brazilian Real (BRL) currency.
 */
public class MoneyUtils {

    private static final Locale PT_BR = new Locale("pt", "BR");

    /**
     * Formats the given value as BRL currency (e.g. {@code R$ 1.234,56}).
     *
     * @param value value to format
     * @return value formatted as BRL currency
     */
    public String formatBRL(double value) {
        return NumberFormat.getCurrencyInstance(PT_BR).format(value);
    }

    /**
     * Formats the given value as BRL currency (e.g. {@code R$ 1.234,56}).
     *
     * @param value value to format
     * @return value formatted as BRL currency
     * @throws NullPointerException if {@code value} is null
     */
    public String formatBRL(BigDecimal value) {
        return NumberFormat.getCurrencyInstance(PT_BR).format(value);
    }
}
