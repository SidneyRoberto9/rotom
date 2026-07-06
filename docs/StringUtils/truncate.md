# StringUtils.truncate(String value, int maxLength)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Corta a string no tamanho máximo informado. Se a string já for menor ou igual ao limite, é
retornada sem alteração.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a truncar |
| `maxLength` | `int` | tamanho máximo permitido (deve ser ≥ 0) |

## Retorno

`String` — string truncada, ou `null` se `value` for `null`.

## Comportamento de borda

- `value == null` → retorna `null` **antes** de checar `maxLength` (checagem de null tem
  prioridade sobre a validação do parâmetro).
- **Lança `IllegalArgumentException("maxLength deve ser >= 0")`** se `maxLength < 0` (só quando
  `value` não é `null`).
- Não adiciona reticências (`...`) nem qualquer sufixo — é um corte puro via `substring(0,
  maxLength)`.

## Exemplo

```java
StringUtils str = new StringUtils();

str.truncate("abcdef", 3);   // "abc"
str.truncate("ab", 10);        // "ab" (já menor que o limite)
str.truncate(null, 3);          // null
str.truncate("abc", -1);        // lança IllegalArgumentException
```
