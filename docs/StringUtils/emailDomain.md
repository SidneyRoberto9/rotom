# StringUtils.emailDomain(String email)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Extrai o domínio de um e-mail em maiúsculas, considerando apenas o primeiro rótulo antes do ponto.
Exemplo: `"user@gmail.com"` → `"GMAIL"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `email` | `String` | e-mail válido contendo `@` |

## Retorno

`String` — domínio em maiúsculas (só o primeiro rótulo, sem TLD).

## Comportamento de borda

- **Lança `IllegalArgumentException("Email inválido: " + email)`** se `email` for blank
  ([`isBlank`](isBlank.md)) ou não contiver `@`.
- Divide por `@` e pega `parts[1]`, depois divide por `.` e pega `parts[0]` — para domínios com
  múltiplos subdomínios (ex. `user@mail.empresa.com.br`), retorna só `"MAIL"`, não o domínio
  completo.
- Faz `email.trim()` antes de dividir, mas não faz `toLowerCase()` antes — o resultado final é
  forçado para maiúsculas via `.toUpperCase()`.

## Exemplo

```java
StringUtils str = new StringUtils();

str.emailDomain("user@gmail.com");            // "GMAIL"
str.emailDomain("user@mail.empresa.com.br");  // "MAIL" (só o 1º rótulo)
str.emailDomain("invalido");                    // lança IllegalArgumentException
str.emailDomain(null);                           // lança IllegalArgumentException
```
