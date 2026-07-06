# EncodingUtils.decodeInt(String encoded)

**Pacote:** `io.github.sidneyroberto9.rotom.encoding`
**Arquivo:** `EncodingUtils.java`

## Descrição

Decodifica uma string Base64 gerada por [`encodeInt`](encodeInt.md) de volta ao inteiro original.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `encoded` | `String` | string Base64 produzida por `encodeInt` |

## Retorno

`int` — inteiro decodificado.

## Comportamento de borda

- **Lança `IllegalArgumentException`** se `encoded` não for Base64 válido, **ou** se decodificar
  para um array de bytes com tamanho diferente de 4 (`Integer.BYTES`) — proteção extra que o
  `Base64Encoder.decode` original do m4all não tinha (lá, um array de tamanho errado faria
  `ByteBuffer.getInt()` lançar `BufferUnderflowException` sem mensagem clara).
- **Lança `NullPointerException`** se `encoded` for `null`.

## Exemplo

```java
EncodingUtils encoding = new EncodingUtils();

encoding.decodeInt("AAAAKg==");  // 42
encoding.decodeInt("aGVsbG8=");   // lança IllegalArgumentException (5 bytes, não 4)
```
