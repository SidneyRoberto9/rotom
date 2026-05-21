package br.com.sidneyroberto9.utils.cep.domain;

import java.util.Objects;

public class Address {

    private final String uf;
    private final String cep;
    private final String bairro;
    private final String localidade;
    private final String logradouro;
    private final String complemento;
    private final String ibge;
    private final String gia;
    private final String ddd;
    private final String siafi;
    private final String unidade;
    private final String estado;
    private final String regiao;

    public Address(
            String uf,
            String cep,
            String bairro,
            String localidade,
            String logradouro,
            String complemento,
            String ibge,
            String gia,
            String ddd,
            String siafi,
            String unidade,
            String estado,
            String regiao
    ) {
        this.uf = uf;
        this.cep = cep;
        this.bairro = bairro;
        this.localidade = localidade;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
        this.unidade = unidade;
        this.estado = estado;
        this.regiao = regiao;
    }

    public String getUf() { return uf; }
    public String getCep() { return cep; }
    public String getBairro() { return bairro; }
    public String getLocalidade() { return localidade; }
    public String getLogradouro() { return logradouro; }
    public String getComplemento() { return complemento; }
    public String getIbge() { return ibge; }
    public String getGia() { return gia; }
    public String getDdd() { return ddd; }
    public String getSiafi() { return siafi; }
    public String getUnidade() { return unidade; }
    public String getEstado() { return estado; }
    public String getRegiao() { return regiao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address that = (Address) o;
        return Objects.equals(uf, that.uf)
                && Objects.equals(cep, that.cep)
                && Objects.equals(bairro, that.bairro)
                && Objects.equals(localidade, that.localidade)
                && Objects.equals(logradouro, that.logradouro)
                && Objects.equals(complemento, that.complemento)
                && Objects.equals(ibge, that.ibge)
                && Objects.equals(gia, that.gia)
                && Objects.equals(ddd, that.ddd)
                && Objects.equals(siafi, that.siafi)
                && Objects.equals(unidade, that.unidade)
                && Objects.equals(estado, that.estado)
                && Objects.equals(regiao, that.regiao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uf, cep, bairro, localidade, logradouro, complemento,
                ibge, gia, ddd, siafi, unidade, estado, regiao);
    }

    @Override
    public String toString() {
        return "Address{" +
                "uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                ", unidade='" + unidade + '\'' +
                ", estado='" + estado + '\'' +
                ", regiao='" + regiao + '\'' +
                '}';
    }
}
