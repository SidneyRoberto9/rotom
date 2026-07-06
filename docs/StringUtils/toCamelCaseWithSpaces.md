# StringUtils.toCamelCaseWithSpaces(String input)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

*Introduzido na v1.1.0*, a partir de `StringFormatUtil.toCamelCaseWithSpaces` do m4all
(ver [`docs/AUDIT.md`](../AUDIT.md)).

## Descrição

Converte o texto para camel case com espaços: cada palavra é colocada em minúsculas e depois tem
sua primeira letra capitalizada. Exemplo: `"HELLO WORLD"` → `"Hello World"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `input` | `String` | texto a converter |

## Retorno

`String` — texto convertido, ou `""` se `input` for `null` ou vazio.

## Comportamento de borda

- `input == null` ou `input.isEmpty()` → `""`.
- Diferente de [`capitalizeWords`](capitalizeWords.md): **não** preserva conectivos PT em
  minúsculas (`"joão da silva"` → `"Joao Da Silva"` aqui, vs. `"João da Silva"` em
  `capitalizeWords`) — este método é uma capitalização simples palavra a palavra.
- Divide por espaços em branco (`\s+`) após `trim()`; palavras vazias resultantes de espaços
  múltiplos são ignoradas.

## Exemplo

```java
StringUtils str = new StringUtils();

str.toCamelCaseWithSpaces("HELLO WORLD");    // "Hello World"
str.toCamelCaseWithSpaces("joão da silva");  // "Joao Da Silva" (sem preservar conectivos)
str.toCamelCaseWithSpaces(null);               // ""
```
