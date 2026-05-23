package br.com.sidneyroberto9.utils.cep.domain;

import br.com.sidneyroberto9.utils.cep.infra.provider.*;

import java.util.List;
import java.util.Optional;

/**
 * Service for address lookup by CEP (Brazilian postal code) with a multi-provider fallback strategy.
 * Tries each provider in order; if one fails or returns no data, the next one is attempted automatically.
 * Default providers: ViaCEP, OpenCEP, BrasilCEP, CEP.Rest, Zippopotam.
 */
public class CepService {

    private final List<CepProvider> providers;
    private final CepUtils cepUtils;

    /**
     * Creates the service with the 5 default providers in priority order.
     */
    public CepService() {
        this(List.of(
                new ViaCepProvider(),
                new OpenCepProvider(),
                new BrasilCepProvider(),
                new CepRestProvider(),
                new ZippopotamProvider()
        ));
    }

    /**
     * Creates the service with a custom list of providers.
     *
     * @param providers list of providers to use, in priority order
     */
    public CepService(List<CepProvider> providers) {
        this.providers = providers;
        this.cepUtils = new CepUtils();
    }

    /**
     * Looks up the address for the given CEP by querying providers in sequence.
     * If no provider returns data, returns an {@link Address} with all fields null except the CEP itself.
     *
     * @param rawCep CEP with or without mask (e.g. {@code 58038-000} or {@code 58038000})
     * @return {@link Address} containing the found address data
     * @throws IllegalArgumentException if the CEP does not have exactly 8 valid digits
     */
    public Address lookup(String rawCep) {
        String cep = this.cepUtils.normalize(rawCep);

        if (!this.cepUtils.isValid(cep)) {
            throw new IllegalArgumentException("CEP inválido: " + rawCep);
        }

        for (CepProvider provider : this.providers) {
            try {
                Optional<Address> result = provider.fetch(cep);

                if (result.isPresent()) {
                    return result.get();
                }
            } catch (Exception ignored) {
            }
        }

        return new Address(null, cep, null, null, null, null, null, null, null, null, null, null, null);
    }
}
