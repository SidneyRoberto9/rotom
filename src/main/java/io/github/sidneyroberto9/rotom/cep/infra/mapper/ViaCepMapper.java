package io.github.sidneyroberto9.rotom.cep.infra.mapper;

import io.github.sidneyroberto9.rotom.cep.domain.Address;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public final class ViaCepMapper {

    private ViaCepMapper() {
    }

    public static Optional<Address> map(JsonNode data) {
        JsonNode erro = data.path("erro");

        if (!erro.isMissingNode() && erro.asBoolean()) {
            return Optional.empty();
        }

        return Optional.of(new Address(
                JsonHelper.str(data, "uf"),
                JsonHelper.str(data, "cep"),
                JsonHelper.str(data, "bairro"),
                JsonHelper.str(data, "localidade"),
                JsonHelper.str(data, "logradouro"),
                JsonHelper.str(data, "complemento"),
                JsonHelper.str(data, "ibge"),
                JsonHelper.str(data, "gia"),
                JsonHelper.str(data, "ddd"),
                JsonHelper.str(data, "siafi"),
                JsonHelper.str(data, "unidade"),
                JsonHelper.str(data, "estado"),
                JsonHelper.str(data, "regiao")
        ));
    }
}
