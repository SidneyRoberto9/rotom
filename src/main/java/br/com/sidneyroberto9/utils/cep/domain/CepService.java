package br.com.sidneyroberto9.utils.cep.domain;

import br.com.sidneyroberto9.utils.cep.infra.provider.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CepService {

    private static final Logger log = Logger.getLogger(CepService.class.getName());

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

    public Optional<Address> lookup(String rawCep) {
        String cep = this.cepUtils.normalize(rawCep);

        if (!this.cepUtils.isValid(cep)) {
            throw new IllegalArgumentException("CEP inválido: " + rawCep);
        }

        for (CepProvider provider : this.providers) {
            long start = System.currentTimeMillis();

            try {
                Optional<Address> result = provider.fetch(cep);

                long ms = System.currentTimeMillis() - start;

                if (result.isPresent()) {
                    log.info("provider=" + provider.name() + " ms=" + ms + " status=success");
                    return result;
                }

                log.warning("provider=" + provider.name() + " ms=" + ms + " status=no_data");
            } catch (Exception e) {
                long ms = System.currentTimeMillis() - start;
                log.warning("provider=" + provider.name() + " ms=" + ms + " error=" + e.getMessage());
            }
        }

        return Optional.empty();
    }
}
