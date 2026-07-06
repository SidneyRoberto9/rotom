# EncodingUtils.encodeInt(int value)

**Pacote:** `io.github.sidneyroberto9.rotom.encoding`
**Arquivo:** `EncodingUtils.java`

## Descrição

Codifica um inteiro em Base64, usando sua representação de 4 bytes big-endian
(`ByteBuffer.allocate(Integer.BYTES).putInt(value)`). Útil para ofuscar IDs sequenciais em URLs.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `int` | inteiro a codificar |

## Retorno

`String` — representação Base64 do inteiro (sempre 8 caracteres, incluindo padding `=`).

## Comportamento de borda

- Determinístico: o mesmo `value` sempre produz o mesmo resultado (não é criptografia, é apenas
  ofuscação reversível — qualquer um pode decodificar com [`decodeInt`](decodeInt.md)).
- Funciona para todo o intervalo de `int`, incluindo negativos.

## Exemplo

```java
EncodingUtils encoding = new EncodingUtils();

encoding.encodeInt(42);   // ex. "AAAAKg=="
encoding.decodeInt(encoding.encodeInt(42));  // 42 (round-trip)
```
