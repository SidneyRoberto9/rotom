package br.com.sidneyroberto9.utils.cep.infra.provider;

import br.com.sidneyroberto9.utils.cep.domain.CepProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

abstract class BaseProvider implements CepProvider {

    protected static final OkHttpClient HTTP = new OkHttpClient();
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    protected JsonNode get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        try (Response response = HTTP.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("HTTP " + response.code() + " from " + url);
            }

            ResponseBody body = response.body();

            if (body == null) {
                throw new IOException("Empty body from " + url);
            }

            return MAPPER.readTree(body.string());
        }
    }
}
