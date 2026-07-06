package io.github.sidneyroberto9.rotom.date.holiday;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Service for fetching Brazilian national holidays for a given year from the public
 * <a href="https://brasilapi.com.br/docs#tag/Feriados-Nacionais">BrasilAPI</a>.
 * Unlike {@link io.github.sidneyroberto9.rotom.date.DateService}, which relies on the offline
 * jollyday calendar, this service performs a live HTTP call and reflects BrasilAPI's data as-is.
 */
public class BrasilApiHolidayService {

    private static final String HOLIDAYS_URL = "https://brasilapi.com.br/api/feriados/v1/";

    private static final OkHttpClient HTTP = new OkHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Fetches the Brazilian national holidays for the given year.
     *
     * @param year year to fetch holidays for (e.g. {@code 2026})
     * @return set of holiday dates for the given year
     * @throws IOException if the request fails or the response cannot be parsed
     */
    public Set<LocalDate> getHolidays(int year) throws IOException {
        JsonNode holidays = this.get(HOLIDAYS_URL + year);
        Set<LocalDate> dates = new LinkedHashSet<>();

        for (JsonNode holiday : holidays) {
            dates.add(LocalDate.parse(holiday.get("date").asText()));
        }

        return dates;
    }

    private JsonNode get(String url) throws IOException {
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
