package br.com.sidneyroberto9.utils.cep.infra.provider;

import br.com.sidneyroberto9.utils.cep.domain.Address;
import br.com.sidneyroberto9.utils.cep.infra.mapper.BrasilCepMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Optional;

public class BrasilCepProvider extends BaseProvider {

    @Override
    public String name() {
        return "BrasilCEP";
    }

    @Override
    public Optional<Address> fetch(String cep) throws IOException {
        JsonNode data = this.get("https://brasilcep.dev/api/cep/" + cep);

        return BrasilCepMapper.map(data);
    }
}
