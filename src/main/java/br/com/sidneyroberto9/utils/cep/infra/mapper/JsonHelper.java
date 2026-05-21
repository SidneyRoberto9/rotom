package br.com.sidneyroberto9.utils.cep.infra.mapper;

import com.fasterxml.jackson.databind.JsonNode;

final class JsonHelper {

    private JsonHelper() {}

    static String str(JsonNode node, String field) {
        JsonNode value = node.path(field);

        if (value.isMissingNode() || value.isNull()) {
            return null;
        }

        String text = value.asText("").trim();

        if (text.isBlank()) {
            return null;
        }

        return text;
    }
}
