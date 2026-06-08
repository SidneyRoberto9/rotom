package br.io.sidneyroberto9.utils.cep.infra.mapper;

import br.io.sidneyroberto9.utils.cep.domain.Address;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public final class CepRestMapper {

    private CepRestMapper() {
    }

    public static Optional<Address> map(JsonNode data) {
        if (JsonHelper.str(data, "cep") == null) {
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
                null, null, null, null, null, null
        ));
    }
}
