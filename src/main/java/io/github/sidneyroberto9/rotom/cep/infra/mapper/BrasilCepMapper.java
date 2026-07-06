package io.github.sidneyroberto9.rotom.cep.infra.mapper;

import io.github.sidneyroberto9.rotom.cep.domain.Address;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public final class BrasilCepMapper {

    private BrasilCepMapper() {
    }

    public static Optional<Address> map(JsonNode data) {
        if (JsonHelper.str(data, "cep") == null) {
            return Optional.empty();
        }

        return Optional.of(new Address(
                JsonHelper.str(data, "state"),
                JsonHelper.str(data, "cep"),
                JsonHelper.str(data, "district"),
                JsonHelper.str(data, "city"),
                JsonHelper.str(data, "street"),
                null, null, null, null, null, null, null, null
        ));
    }
}
