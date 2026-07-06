package io.github.sidneyroberto9.rotom.mask;

/**
 * Utility class for masking sensitive document numbers (LGPD-friendly display masking).
 * Unlike {@link io.github.sidneyroberto9.rotom.cpf.CPFService} and
 * {@link io.github.sidneyroberto9.rotom.cnpj.CNPJService}, these methods obscure most digits
 * rather than just applying the standard punctuation mask.
 */
public class MaskUtils {

    /**
     * Masks a CPF, revealing only the first 3 and last 2 digits.
     * Example: {@code "12345678909"} → {@code "123.***.***-09"}.
     *
     * @param cpf CPF with or without mask
     * @return masked CPF, or {@code null} if the input is null, or {@code "***********"} if it does not have exactly 11 digits
     */
    public String maskCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        String digits = cpf.replaceAll("\\D", "");

        if (digits.length() != 11) {
            return "***********";
        }

        return digits.substring(0, 3) + ".***.***-" + digits.substring(9);
    }

    /**
     * Masks a CNPJ, revealing only the first 2 digits and the branch block (digits 9–12).
     * Example: {@code "12345678000195"} → {@code "12.***.***&#47;0001-**"}.
     *
     * @param cnpj CNPJ with or without mask
     * @return masked CNPJ, or {@code null} if the input is null, or {@code "**************"} if it does not have exactly 14 digits
     */
    public String maskCnpj(String cnpj) {
        if (cnpj == null) {
            return null;
        }

        String digits = cnpj.replaceAll("\\D", "");

        if (digits.length() != 14) {
            return "**************";
        }

        return digits.substring(0, 2) + ".***.***/" + digits.substring(8, 12) + "-**";
    }
}
