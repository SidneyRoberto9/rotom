# PasswordValidator.isStrong(String password)

**Pacote:** `io.github.sidneyroberto9.rotom.validation`
**Arquivo:** `PasswordValidator.java`

## Descrição

Verifica se a senha é forte: 8 a 16 caracteres, com pelo menos um dígito, uma letra minúscula, uma
letra maiúscula, um caractere especial do conjunto `@#_$%^&<>?,/:;|'"+!.}(){%~^$`, e sem espaços em
branco.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `password` | `String` | senha a validar |

## Retorno

`boolean` — `true` se atende a todos os critérios, `false` caso contrário.

## Comportamento de borda

- `password == null` → `false` (não lança exceção).
- Comprimento fora de `8`–`16` → `false` (nem menor, nem maior).
- **Qualquer espaço em branco no meio da senha invalida** (`(?=\S+$)`), inclusive se todos os
  outros critérios forem atendidos.
- O regex exige **todas** as categorias simultaneamente — falta de só uma (ex. sem caractere
  especial) já invalida.
- Regex copiado fielmente do `PasswordValidator` original do m4all, incluindo a classe de
  caracteres especiais duplicada (`%`, `^`, `$` aparecem mais de uma vez no conjunto, sem efeito
  adicional).

## Exemplo

```java
PasswordValidator passwordValidator = new PasswordValidator();

passwordValidator.isStrong("Abc123!@");   // true
passwordValidator.isStrong("abc123!@");    // false (sem maiúscula)
passwordValidator.isStrong("Abc 123!@");   // false (contém espaço)
passwordValidator.isStrong(null);            // false
```
