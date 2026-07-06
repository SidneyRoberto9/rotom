# StringUtils.normalizeEmail(String email)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Normaliza um endereço de e-mail removendo espaços das pontas e convertendo para minúsculas.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `email` | `String` | e-mail a normalizar |

## Retorno

`String` — e-mail normalizado, ou `null` se o input for `null`.

## Comportamento de borda

- `email == null` → `null`.
- **Não valida** que a string seja um e-mail (não checa `@`) — apenas `trim().toLowerCase()`. Para
  operações que exigem `@`, veja [`emailDomain`](emailDomain.md) e [`maskEmail`](maskEmail.md).

## Exemplo

```java
StringUtils str = new StringUtils();

str.normalizeEmail("  Usuario@GMAIL.com  ");  // "usuario@gmail.com"
str.normalizeEmail(null);                       // null
```
