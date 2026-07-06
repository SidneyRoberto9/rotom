# StringUtils.firstTwoNames(String fullName)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Extrai e capitaliza os dois primeiros nomes de um nome completo.
Exemplo: `"joão da silva souza"` → `"João Da"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `fullName` | `String` | nome completo |

## Retorno

`String` — os dois primeiros nomes capitalizados (separados por espaço), ou `""` se `null`.

## Comportamento de borda

- `fullName == null` → `""`.
- Se houver **apenas uma palavra**, retorna só ela capitalizada (sem erro de índice).
- **Não filtra conectivos** como `capitalizeWords` faz — se o 2º "nome" for um conectivo (`"da"`,
  `"de"`), ele é capitalizado normalmente e incluído no resultado (ex. `"João Da"`, não `"João
  Silva"`). Isso é uma diferença importante em relação a `capitalizeWords`.

## Exemplo

```java
StringUtils str = new StringUtils();

str.firstTwoNames("João da Silva Souza");  // "João Da"
str.firstTwoNames("Maria");                  // "Maria"
str.firstTwoNames(null);                      // ""
```
