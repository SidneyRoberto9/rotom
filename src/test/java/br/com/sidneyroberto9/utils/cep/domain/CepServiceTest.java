package br.com.sidneyroberto9.utils.cep.domain;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CepServiceTest {

    @Test
    void lookup_returns_empty_when_all_providers_fail() {
        CepProvider alwaysFails = new CepProvider() {
            @Override
            public String name() { return "Failing"; }

            @Override
            public Optional<Address> fetch(String cep) throws IOException {
                throw new IOException("network error");
            }
        };

        CepService service = new CepService(List.of(alwaysFails));
        Optional<Address> result = service.lookup("01310100");

        assertTrue(result.isEmpty());
    }

    @Test
    void lookup_returns_empty_when_all_providers_return_no_data() {
        CepProvider noData = new CepProvider() {
            @Override
            public String name() { return "NoData"; }

            @Override
            public Optional<Address> fetch(String cep) {
                return Optional.empty();
            }
        };

        CepService service = new CepService(List.of(noData));
        Optional<Address> result = service.lookup("01310100");

        assertTrue(result.isEmpty());
    }

    @Test
    void lookup_returns_first_successful_provider() {
        Address expected = new Address("SP", "01310100", null, "São Paulo",
                "Avenida Paulista", null, null, null, null, null, null, null, null);

        CepProvider fails = new CepProvider() {
            @Override
            public String name() { return "Failing"; }

            @Override
            public Optional<Address> fetch(String cep) throws IOException {
                throw new IOException("network error");
            }
        };

        CepProvider succeeds = new CepProvider() {
            @Override
            public String name() { return "Succeeding"; }

            @Override
            public Optional<Address> fetch(String cep) {
                return Optional.of(expected);
            }
        };

        CepService service = new CepService(List.of(fails, succeeds));
        Optional<Address> result = service.lookup("01310100");

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void lookup_throws_on_invalid_cep() {
        CepService service = new CepService(List.of());
        assertThrows(IllegalArgumentException.class, () -> service.lookup("123"));
    }

    @Test
    void lookup_normalizes_cep_before_fetching() {
        CepService service = new CepService(List.of(new CepProvider() {
            @Override
            public String name() { return "Echo"; }

            @Override
            public Optional<Address> fetch(String cep) {
                assertEquals("01310100", cep);
                return Optional.empty();
            }
        }));

        service.lookup("01310-100");
    }
}
