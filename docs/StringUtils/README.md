# StringUtils

Pacote: `io.github.sidneyroberto9.rotom.strings`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/strings/StringUtils.java`

Classe utilitária para manipulação de strings com suporte a português brasileiro: capitalização
com conectivos, slugificação, remoção de acentos e operações de e-mail. Todos os métodos são de
**instância**.

## Construtor

```java
StringUtils str = new StringUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `StringUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`isBlank(String value)`](isBlank.md) | `boolean` | null/vazio/só espaços |
| [`isNotBlank(String value)`](isNotBlank.md) | `boolean` | negação de `isBlank` |
| [`trimOrNull(String value)`](trimOrNull.md) | `String` | trim ou `null` se blank |
| [`requireNonBlank(String value, String message)`](requireNonBlank.md) | `String` | trim ou lança exceção |
| [`capitalize(String word)`](capitalize.md) | `String` | 1ª letra maiúscula |
| [`capitalizeWords(String fullName)`](capitalizeWords.md) | `String` | capitaliza respeitando conectivos PT |
| [`firstTwoNames(String fullName)`](firstTwoNames.md) | `String` | primeiros 2 nomes capitalizados |
| [`digitsOnly(String input)`](digitsOnly.md) | `String` | só dígitos |
| [`alphanumericOnly(String input)`](alphanumericOnly.md) | `String` | só letras/dígitos Unicode |
| [`removeAccents(String input)`](removeAccents.md) | `String` | remove acentuação |
| [`slugify(String input)`](slugify.md) | `String` | gera slug de URL |
| [`truncate(String value, int maxLength)`](truncate.md) | `String` | corta no tamanho máximo |
| [`containsIgnoreCase(String text, String search)`](containsIgnoreCase.md) | `boolean` | contains case-insensitive |
| [`normalizeEmail(String email)`](normalizeEmail.md) | `String` | trim + lowercase |
| [`emailDomain(String email)`](emailDomain.md) | `String` | domínio em maiúsculas |
| [`maskEmail(String email)`](maskEmail.md) | `String` | mascara parte local |
| [`encodeUrl(String baseUrl, String text)`](encodeUrl.md) | `String` | URL-encode e concatena |
| [`toCamelCaseWithSpaces(String input)`](toCamelCaseWithSpaces.md) | `String` | camelCase com espaços (introduzido v1.1.0) |
| [`nameInitials(String fullName)`](nameInitials.md) | `String` | iniciais estilo avatar (introduzido v1.1.0) |
| [`formatElevenDigits(String input)`](formatElevenDigits.md) | `String` | formata registro de 11 dígitos (introduzido v1.1.0) |
| [`defaultIfBlank(String value, String defaultValue)`](defaultIfBlank.md) | `String` | valor ou default se blank (introduzido v1.1.0) |
