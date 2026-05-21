package br.com.sidneyroberto9.utils.formatter;

public class CNPJFormatter {

    public static String format(String cnpj) {

        cnpj = cnpj.replaceAll("\\D", "");

        return cnpj.replaceFirst(
                "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})",
                "$1.$2.$3/$4-$5"
        );
    }
}