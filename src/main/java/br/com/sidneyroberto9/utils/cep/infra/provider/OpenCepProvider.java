package br.com.sidneyroberto9.utils.cep.infra.provider;

import br.com.sidneyroberto9.utils.cep.domain.Address;
import br.com.sidneyroberto9.utils.cep.infra.mapper.OpenCepMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Optional;

public class OpenCepProvider extends BaseProvider {

    @Override
    public String name() {
        return "OpenCEP";
    }

    @Override
    public Optional<Address> fetch(String cep) throws IOException {
        JsonNode data = this.get("https://opencep.com/v1/" + cep);

        return OpenCepMapper.map(data);
    }
}
