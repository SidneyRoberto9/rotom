package io.github.sidneyroberto9.rotom.cep.domain;

import java.io.IOException;
import java.util.Optional;

public interface CepProvider {
    String name();

    Optional<Address> fetch(String cep) throws IOException;
}
