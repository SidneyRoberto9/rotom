package br.io.sidneyroberto9.utils.cep.domain;

import java.io.IOException;
import java.util.Optional;

public interface CepProvider {
    String name();

    Optional<Address> fetch(String cep) throws IOException;
}
