# StringUtils.formatElevenDigits(String input)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

*Introduzido na v1.1.0*, a partir de `StringFormatUtil.formatElevenDigitString` do m4all
(ver [`docs/AUDIT.md`](../AUDIT.md)).

## Descrição

Formata uma string de 11 dígitos (ex. inscrição estadual) como `SS.T.RRRRRRR-D` (estado, tipo
jurídico, registro, dígito verificador).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `input` | `String` | string contendo 11 dígitos, com ou sem máscara |

## Retorno

`String` — string formatada, ou **o input original sem alteração** se, após remover a máscara, não
tiver exatamente 11 dígitos, ou `null` se o input for `null`.

## Comportamento de borda

- `input == null` → `null`.
- Comprimento diferente de 11 dígitos (após [`digitsOnly`](digitsOnly.md)) → **retorna o `input`
  original, tal como recebido** (não lança exceção, não retorna `null`/`""`).
- Não valida se os dígitos formam um registro real — é formatação posicional pura.

## Exemplo

```java
StringUtils str = new StringUtils();

str.formatElevenDigits("12345678901");  // "12.3.4567890-1"
str.formatElevenDigits("123");            // "123" (comprimento inválido, retorna sem alteração)
str.formatElevenDigits(null);              // null
```
