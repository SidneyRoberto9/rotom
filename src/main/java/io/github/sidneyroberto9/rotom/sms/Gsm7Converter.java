package io.github.sidneyroberto9.rotom.sms;

import java.util.Map;

/**
 * Utility class for converting UTF-8 text to the GSM 7-bit default alphabet used by SMS bodies.
 * Characters already within the GSM-7 charset pass through unchanged; accented characters are
 * folded to their closest ASCII equivalent; any other character is silently dropped.
 */
public class Gsm7Converter {

    private static final Map<Character, Character> ACCENT_FALLBACK = Map.ofEntries(
            Map.entry('á', 'a'), Map.entry('à', 'a'), Map.entry('â', 'a'), Map.entry('ã', 'a'), Map.entry('ä', 'a'),
            Map.entry('Á', 'A'), Map.entry('À', 'A'), Map.entry('Â', 'A'), Map.entry('Ã', 'A'), Map.entry('Ä', 'A'),
            Map.entry('é', 'e'), Map.entry('è', 'e'), Map.entry('ê', 'e'), Map.entry('ë', 'e'),
            Map.entry('É', 'E'), Map.entry('È', 'E'), Map.entry('Ê', 'E'), Map.entry('Ë', 'E'),
            Map.entry('í', 'i'), Map.entry('ì', 'i'), Map.entry('î', 'i'), Map.entry('ï', 'i'),
            Map.entry('Í', 'I'), Map.entry('Ì', 'I'), Map.entry('Î', 'I'), Map.entry('Ï', 'I'),
            Map.entry('ó', 'o'), Map.entry('ò', 'o'), Map.entry('ô', 'o'), Map.entry('õ', 'o'), Map.entry('ö', 'o'),
            Map.entry('Ó', 'O'), Map.entry('Ò', 'O'), Map.entry('Ô', 'O'), Map.entry('Õ', 'O'), Map.entry('Ö', 'O'),
            Map.entry('ú', 'u'), Map.entry('ù', 'u'), Map.entry('û', 'u'), Map.entry('ü', 'u'),
            Map.entry('Ú', 'U'), Map.entry('Ù', 'U'), Map.entry('Û', 'U'), Map.entry('Ü', 'U'),
            Map.entry('ç', 'c'), Map.entry('Ç', 'C'),
            Map.entry('ñ', 'n'), Map.entry('Ñ', 'N'),
            Map.entry('ý', 'y'), Map.entry('ÿ', 'y'), Map.entry('Ý', 'Y')
    );

    private static final String GSM_7_CHARSET =
            "@£$¥èéùìòÇ\nØø\rÅåΔ_ΦΓΛΩΠΨΣΘΞÆæßÉ " +
                    "!\"#¤%&'()*+,-./0123456789:;<=>?" +
                    "¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà";

    /**
     * Converts the given text to the GSM 7-bit default alphabet.
     * Characters already in the GSM-7 charset are kept as-is; mapped accented characters are
     * folded to ASCII; any other unsupported character is dropped from the result.
     *
     * @param input text to convert (typically an SMS body)
     * @return text restricted to the GSM-7 charset
     * @throws NullPointerException if {@code input} is null
     */
    public String convertToGsm7(String input) {
        StringBuilder result = new StringBuilder(input.length());

        for (char c : input.toCharArray()) {
            if (GSM_7_CHARSET.indexOf(c) != -1) {
                result.append(c);
            } else if (ACCENT_FALLBACK.containsKey(c)) {
                result.append(ACCENT_FALLBACK.get(c));
            }
        }

        return result.toString();
    }
}
