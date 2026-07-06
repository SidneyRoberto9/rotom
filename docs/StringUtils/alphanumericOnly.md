# StringUtils.alphanumericOnly(String input)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Remove todos os caracteres que não são letras ou dígitos, com suporte Unicode (`\p{L}` e `\p{Nd}`
— então letras acentuadas como `ã`, `ç` são preservadas, não apenas ASCII).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `input` | `String` | string a processar |

## Retorno

`String` — apenas letras e dígitos, ou `""` se `null` ou vazio.

## Comportamento de borda

- `input == null` ou `input.isEmpty()` → `""`.
- Preserva acentos e letras não-ASCII (usa classes Unicode `\p{L}\p{Nd}`, não `[a-zA-Z0-9]`) — para
  remover acentos, use [`removeAccents`](removeAccents.md) antes.

## Exemplo

```java
StringUtils str = new StringUtils();

str.alphanumericOnly("Olá, Mundo! 123");  // "OláMundo123"
str.alphanumericOnly(null);                 // ""
```
