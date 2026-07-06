# RandomUtil

Pacote: `io.github.sidneyroberto9.rotom.random`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/random/RandomUtil.java`

Utilitário para geração de números, strings, tokens e API keys aleatórios. Baseado em
`SecureRandom` (aleatoriedade criptograficamente forte). Introduzido na v1.1.0 a partir da
auditoria do monorepo m4all (`RandomUtil` + `EncodeService.generateRandomCode` +
`PasswordUtil.getToken` + `ApiKeyGenerator`, ver [`docs/AUDIT.md`](../AUDIT.md)).

## Construtor

```java
RandomUtil random = new RandomUtil();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `RandomUtil` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`randomInt(int min, int max)`](randomInt.md) | `int` | inteiro aleatório no intervalo `[min, max]` |
| [`randomAlphanumeric(int size)`](randomAlphanumeric.md) | `String` | string aleatória A-Z0-9 |
| [`randomNumeric(int size)`](randomNumeric.md) | `String` | string aleatória só dígitos |
| [`randomCode(int size)`](randomCode.md) | `String` | alias de `randomAlphanumeric`, p/ códigos de verificação |
| [`token(int limit)`](token.md) | `String` | token truncado de UUID sem hífens |
| [`apiKey()` / `apiKey(String prefix)`](apiKey.md) | `String` | API key com prefixo + 12 chars hex |
