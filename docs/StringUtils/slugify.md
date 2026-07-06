# StringUtils.slugify(String input)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Gera um slug amigável para URL: minúsculas, remove acentos, troca espaços por hífen, remove
caracteres especiais, colapsa hífens repetidos e remove hífens nas pontas.
Exemplo: `"Olá Mundo!"` → `"ola-mundo"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `input` | `String` | texto a converter |

## Retorno

`String` — slug gerado, ou `""` se `input` for `null`.

## Comportamento de borda

- `input == null` → `""`.
- Pipeline interno, nesta ordem: `toLowerCase()` → [`removeAccents`](removeAccents.md) → espaços
  viram `-` → remove tudo fora de `[a-z0-9-]` → colapsa `-{2,}` em um único `-` → remove `-` nas
  pontas (regex `^-|-$`).
- Como a remoção de caracteres inválidos roda **depois** da troca de espaço por hífen, símbolos
  isolados entre palavras (ex. `"a & b"`) colapsam corretamente em um único hífen entre `a` e `b`.

## Exemplo

```java
StringUtils str = new StringUtils();

str.slugify("Olá Mundo!");        // "ola-mundo"
str.slugify("Café & Açúcar");     // "cafe-acucar"
str.slugify(null);                  // ""
```
