# EmailValidator.isValid(String email)

**Pacote:** `io.github.sidneyroberto9.rotom.validation`
**Arquivo:** `EmailValidator.java`

## Descrição

Verifica se o texto informado é um endereço de e-mail estruturalmente válido, via expressão
regular (`^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `email` | `String` | e-mail a validar |

## Retorno

`boolean` — `true` se estruturalmente válido, `false` caso contrário.

## Comportamento de borda

- `email == null` → `false` (não lança exceção).
- Faz `trim()` antes de validar — espaços nas pontas não invalidam.
- **Valida só a estrutura** (formato `local@dominio.tld`) — **não** verifica se o domínio existe
  (sem consulta DNS/MX), nem se a caixa postal é entregável. Diferente do
  `ValidationsUtil.isValidEmailAddress` original (que usava `InternetAddress.validate()` da
  JavaMail), este método é puramente sintático.
- Exige um TLD de pelo menos 2 letras (`{2,}`) — `"user@localhost"` (sem ponto) é **inválido**.

## Exemplo

```java
EmailValidator emailValidator = new EmailValidator();

emailValidator.isValid("usuario@gmail.com");  // true
emailValidator.isValid("usuario@localhost");   // false (sem TLD)
emailValidator.isValid(null);                    // false
emailValidator.isValid("não é email");            // false
```
