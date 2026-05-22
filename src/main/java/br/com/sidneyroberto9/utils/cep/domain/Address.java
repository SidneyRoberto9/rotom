package br.com.sidneyroberto9.utils.cep.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String uf;
    private String cep;
    private String bairro;
    private String localidade;
    private String logradouro;
    private String complemento;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private String unidade;
    private String estado;
    private String regiao;
}
