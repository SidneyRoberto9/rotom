package br.com.sidneyroberto9.utils.cep.domain;

import br.com.sidneyroberto9.utils.cep.infra.provider.*;

import java.util.List;
import java.util.Optional;

public class CepService {

    private final List<CepProvider> providers;
    private final CepUtils cepUtils;

    public CepService() {
        this(List.of(
                new ViaCepProvider(),
                new OpenCepProvider(),
                new BrasilCepProvider(),
                new CepRestProvider(),
                new ZippopotamProvider()
        ));
    }

    public CepService(List<CepProvider> providers) {
        this.providers = providers;
        this.cepUtils = new CepUtils();
    }

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
