package io.github.sidneyroberto9.rotom.cep.infra.provider;

import io.github.sidneyroberto9.rotom.cep.domain.Address;
import io.github.sidneyroberto9.rotom.cep.infra.mapper.ViaCepMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Optional;

public class ViaCepProvider extends BaseProvider {

    @Override
    public String name() {
        return "ViaCEP";
    }

    @Override
    public Optional<Address> fetch(String cep) throws IOException {
        JsonNode data = this.get("https://viacep.com.br/ws/" + cep + "/json/");

        return ViaCepMapper.map(data);
    }
}
