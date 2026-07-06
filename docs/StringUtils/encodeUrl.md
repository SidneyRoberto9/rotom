# StringUtils.encodeUrl(String baseUrl, String text)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Codifica `text` como componente de URL (UTF-8) e concatena ao final de `baseUrl`.
Exemplo: `encodeUrl("https://example.com/search?q=", "hello world")` →
`"https://example.com/search?q=hello+world"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `baseUrl` | `String` | URL base, sem o valor do parâmetro |
| `text` | `String` | texto a codificar e anexar |

## Retorno

`String` — `baseUrl` concatenado com `text` URL-encoded.

## Comportamento de borda

- Sem verificação de `null` para nenhum dos dois parâmetros — se `baseUrl` for `null`, o resultado
  é a string `"null" + encoded`; se `text` for `null`, `URLEncoder.encode(null, ...)` **lança
  `NullPointerException`** (comportamento herdado de `URLEncoder`, não tratado pela lib).
- Usa `URLEncoder.encode(text, StandardCharsets.UTF_8)`, que codifica espaço como `+` (padrão
  `application/x-www-form-urlencoded`), não `%20`.
- Não adiciona `?`/`&` — a responsabilidade de montar a query string corretamente é do chamador.

## Exemplo

```java
StringUtils str = new StringUtils();

str.encodeUrl("https://example.com/search?q=", "hello world");
// "https://example.com/search?q=hello+world"

str.encodeUrl("https://example.com/search?q=", null);  // lança NullPointerException
```
