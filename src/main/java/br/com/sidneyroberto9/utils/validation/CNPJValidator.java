package br.com.sidneyroberto9.utils.validation;

public class CNPJValidator {

    public static boolean isValid(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;

        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weights1[i];
        }

        int digit1 = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        sum = 0;

        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weights2[i];
        }

        int digit2 = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        return digit1 == (cnpj.charAt(12) - '0')
                && digit2 == (cnpj.charAt(13) - '0');
    }
}
