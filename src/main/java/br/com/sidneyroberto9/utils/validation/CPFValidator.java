package br.com.sidneyroberto9.utils.validation;

public class CPFValidator {

    public static boolean isValid(String cpf) {

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        int weight = 10;

        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * weight--;
        }

        int digit1 = 11 - (sum % 11);

        if (digit1 > 9) {
            digit1 = 0;
        }

        sum = 0;
        weight = 11;

        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * weight--;
        }

        int digit2 = 11 - (sum % 11);

        if (digit2 > 9) {
            digit2 = 0;
        }

        return digit1 == (cpf.charAt(9) - '0')
                && digit2 == (cpf.charAt(10) - '0');
    }
}