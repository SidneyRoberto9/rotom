# StringUtils.capitalizeWords(String fullName)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Capitaliza cada palavra do texto. Conectivos em português (`das`, `des`, `dos`, `da`, `de`, `do`,
`e`, `a`, `o`, `em`, `na`, `no`, `para`) permanecem em minúsculas quando **não são a primeira
palavra**.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `fullName` | `String` | texto com uma ou mais palavras |

## Retorno

`String` — texto capitalizado respeitando conectivos, ou `""` se `fullName` for `null`.

## Comportamento de borda

- `fullName == null` → retorna `""` (não `null`).
- Divide por espaços em branco (`\s+`) após `trim()`.
- O conectivo **na primeira posição** é sempre capitalizado (a checagem `i > 0` protege isso) —
  ex. `"da Silva"` vira `"Da Silva"`, não `"da Silva"`.
- A lista de conectivos é fixa (case-insensitive na comparação, via `toLowerCase()`), sem opção de
  customização.

## Exemplo

```java
StringUtils str = new StringUtils();

str.capitalizeWords("joão da silva souza");  // "João da Silva Souza"
str.capitalizeWords("da silva");              // "Da Silva" (conectivo é a 1ª palavra)
str.capitalizeWords(null);                     // ""
```
