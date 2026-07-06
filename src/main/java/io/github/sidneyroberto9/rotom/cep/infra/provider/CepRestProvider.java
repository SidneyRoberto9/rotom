package io.github.sidneyroberto9.rotom.cep.infra.provider;

import io.github.sidneyroberto9.rotom.cep.domain.Address;
import io.github.sidneyroberto9.rotom.cep.infra.mapper.CepRestMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Optional;

public class CepRestProvider extends BaseProvider {

    @Override
    public String name() {
        return "CEP.Rest";
    }

    @Override
    public Optional<Address> fetch(String cep) throws IOException {
        JsonNode data = this.get("https://cep.rest/cep/" + cep);

        return CepRestMapper.map(data);
    }
}
