# StringUtils.defaultIfBlank(String value, String defaultValue)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

*Introduzido na v1.1.0*.

## Descrição

Retorna o valor aparado (`trim()`) se não for blank, ou o valor padrão informado caso contrário.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | valor a verificar |
| `defaultValue` | `String` | valor retornado quando `value` é null, vazio, ou só espaços |

## Retorno

`String` — `value.trim()`, ou `defaultValue` se `value` for blank.

## Comportamento de borda

- Usa [`isBlank`](isBlank.md) internamente — cobre `null`, `""` e strings só com espaços do mesmo
  jeito.
- **Não** adiciona vírgula ao final do valor retornado — diferente do
  `emptyStringOrOnlySpace(String)` original do m4all (sem `defaultValue`), que anexava `","` ao
  valor não-blank (comportamento não portado, considerado um efeito colateral indesejado do
  original).
- `defaultValue` não é validado/aparado — é retornado exatamente como passado.

## Exemplo

```java
StringUtils str = new StringUtils();

str.defaultIfBlank("  texto  ", "N/A");  // "texto"
str.defaultIfBlank("   ", "N/A");          // "N/A"
str.defaultIfBlank(null, "N/A");            // "N/A"
```
