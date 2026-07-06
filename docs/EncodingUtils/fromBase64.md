# EncodingUtils.fromBase64(String base64)

**Pacote:** `io.github.sidneyroberto9.rotom.encoding`
**Arquivo:** `EncodingUtils.java`

## Descrição

Decodifica uma string Base64 de volta ao texto original (UTF-8).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `base64` | `String` | string codificada em Base64 |

## Retorno

`String` — texto decodificado.

## Comportamento de borda

- **Lança `IllegalArgumentException`** se `base64` não for uma string Base64 válida (delegado de
  `Base64.getDecoder().decode(...)`).
- **Lança `NullPointerException`** se `base64` for `null`.

## Exemplo

```java
EncodingUtils encoding = new EncodingUtils();

encoding.fromBase64("aGVsbG8gd29ybGQ=");  // "hello world"
encoding.fromBase64("não-base64!!!");       // lança IllegalArgumentException
```
