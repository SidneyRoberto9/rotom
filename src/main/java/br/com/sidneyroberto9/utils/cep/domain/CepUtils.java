package br.com.sidneyroberto9.utils.cep.domain;

import java.util.regex.Pattern;

public class CepUtils {

    private static final Pattern NON_DIGIT = Pattern.compile("\\D");
    private static final Pattern EIGHT_DIGITS = Pattern.compile("\\d{8}");

    public String normalize(String raw) {
        if (raw == null) return null;
        return NON_DIGIT.matcher(raw).replaceAll("");
    }

    public boolean isValid(String cep) {
        if (cep == null) return false;
        return EIGHT_DIGITS.matcher(cep).matches();
    }

    public String format(String cep) {
        String normalized = this.normalize(cep);
        if (!this.isValid(normalized)) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }
        return normalized.substring(0, 5) + "-" + normalized.substring(5);
    }
}
