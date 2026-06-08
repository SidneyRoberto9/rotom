package br.io.sidneyroberto9.utils.cep.infra.provider;

import br.io.sidneyroberto9.utils.cep.domain.Address;
import br.io.sidneyroberto9.utils.cep.infra.mapper.ZippopotamMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Optional;

public class ZippopotamProvider extends BaseProvider {

    @Override
    public String name() {
        return "Zippopotam";
    }

    @Override
    public Optional<Address> fetch(String cep) throws IOException {
        String formatted = cep.substring(0, 5) + "-" + cep.substring(5);

        JsonNode data = this.get("https://api.zippopotam.us/br/" + formatted);

        return ZippopotamMapper.map(data);
    }
}
