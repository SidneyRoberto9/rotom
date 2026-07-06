# CepUtils

Pacote: `io.github.sidneyroberto9.rotom.cep.domain`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/cep/domain/CepUtils.java`

Utilitários puros de CEP (normalização, validação, formatação), **sem nenhuma chamada HTTP**. Para
busca de endereço via rede, use [`CepService`](../CepService/README.md).

## Construtor

```java
CepUtils cepUtils = new CepUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `CepUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`normalize(String raw)`](normalize.md) | `String` | remove máscara, deixa só dígitos |
| [`isValid(String cep)`](isValid.md) | `boolean` | verifica se tem exatamente 8 dígitos |
| [`format(String cep)`](format.md) | `String` | formata para `XXXXX-XXX` |
