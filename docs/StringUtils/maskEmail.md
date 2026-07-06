# StringUtils.maskEmail(String email)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Mascara um e-mail mostrando apenas o primeiro caractere da parte local.
Exemplo: `"user@gmail.com"` → `"u***@gmail.com"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `email` | `String` | e-mail a mascarar |

## Retorno

`String` — e-mail mascarado no formato `X***@dominio`.

## Comportamento de borda

- **Lança `IllegalArgumentException("Email inválido: " + email)`** se `email` for blank ou não
  contiver `@` (mesma checagem de [`emailDomain`](emailDomain.md)).
- Se a parte local tiver **1 único caractere**, esse caractere é usado sem cortar (`local.length()
  > 1 ? local.substring(0,1) : local`) — ex. `"a@gmail.com"` → `"a***@gmail.com"`.
- O domínio completo (`parts[1]`) é preservado sem alteração — inclusive maiúsculas/minúsculas
  originais, sem `trim` adicional além do `email.trim()` inicial.

## Exemplo

```java
StringUtils str = new StringUtils();

str.maskEmail("usuario@gmail.com");  // "u***@gmail.com"
str.maskEmail("a@gmail.com");          // "a***@gmail.com"
str.maskEmail(null);                    // lança IllegalArgumentException
```
