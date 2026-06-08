package br.io.sidneyroberto9.utils.cep.infra.provider;

import br.io.sidneyroberto9.utils.cep.domain.Address;
import br.io.sidneyroberto9.utils.cep.infra.mapper.ViaCepMapper;
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
