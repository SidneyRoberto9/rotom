# EncodingUtils.toBase64(String input)

**Pacote:** `io.github.sidneyroberto9.rotom.encoding`
**Arquivo:** `EncodingUtils.java`

## Descrição

Codifica o texto informado para Base64, usando a codificação de bytes UTF-8.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `input` | `String` | texto a codificar |

## Retorno

`String` — texto codificado em Base64.

## Comportamento de borda

- **Lança `NullPointerException`** se `input` for `null` (chama `input.getBytes(...)` sem
  checagem).
- `""` → codifica para `""`.

## Exemplo

```java
EncodingUtils encoding = new EncodingUtils();

encoding.toBase64("hello world");  // "aGVsbG8gd29ybGQ="
encoding.toBase64(null);            // lança NullPointerException
```
