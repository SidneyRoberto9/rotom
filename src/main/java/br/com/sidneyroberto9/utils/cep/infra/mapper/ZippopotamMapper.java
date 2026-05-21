package br.com.sidneyroberto9.utils.cep.infra.mapper;

import br.com.sidneyroberto9.utils.cep.domain.Address;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public final class ZippopotamMapper {

    private ZippopotamMapper() {}

    public static Optional<Address> map(JsonNode data) {
        JsonNode places = data.path("places");

        if (places.isMissingNode() || !places.isArray() || places.isEmpty()) {
            return Optional.empty();
        }

        JsonNode place = places.get(0);
        String postCode = JsonHelper.str(data, "post code");

        return Optional.of(new Address(
                JsonHelper.str(place, "state abbreviation"),
                postCode,
                null,
                JsonHelper.str(place, "place name"),
                null, null, null, null, null, null, null,
                JsonHelper.str(place, "state"),
                null
        ));
    }
}
