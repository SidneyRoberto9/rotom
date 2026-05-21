package br.com.sidneyroberto9.utils.formatter;

public class CPFFormatter {

    public static String format(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        return cpf.replaceFirst(
                "(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                "$1.$2.$3-$4"
        );
    }
}
