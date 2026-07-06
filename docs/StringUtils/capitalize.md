# StringUtils.capitalize(String word)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Coloca a primeira letra em maiúscula e o restante em minúsculas.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `word` | `String` | palavra a capitalizar |

## Retorno

`String` — palavra capitalizada, ou **o próprio valor de entrada** (não lança erro) se `null` ou
vazio.

## Comportamento de borda

- `word == null` ou `word.isEmpty()` → retorna `word` **sem transformação** (não é `""` nem
  exceção — é o valor original, incluindo `null`).
- Usado internamente por [`capitalizeWords`](capitalizeWords.md) e [`firstTwoNames`](firstTwoNames.md).
- Não separa palavras — funciona sobre uma única "palavra"/token.

## Exemplo

```java
StringUtils str = new StringUtils();

str.capitalize("joão");   // "João"
str.capitalize("JOÃO");   // "João"
str.capitalize("");        // "" (retorna o input, inalterado)
str.capitalize(null);      // null (retorna o input, inalterado)
```
