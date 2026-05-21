package br.com.sidneyroberto9.utils.cep.domain;

import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class Address {
    String uf;
    String cep;
    String bairro;
    String localidade;
    String logradouro;
    String complemento;
    String ibge;
    String gia;
    String ddd;
    String siafi;
    String unidade;
    String estado;
    String regiao;
}
