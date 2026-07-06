# StringUtils.isBlank(String value)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Verifica se a string é `null`, vazia, ou contém apenas espaços em branco (`value.isBlank()`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a verificar |

## Retorno

`boolean` — `true` se null/vazia/blank, `false` caso contrário.

## Comportamento de borda

- Trata `null` com curto-circuito (`value == null || value.isBlank()`), nunca lança NPE.
- Base para vários outros métodos da classe (`isNotBlank`, `trimOrNull`, `requireNonBlank`,
  `emailDomain`, `maskEmail`).

## Exemplo

```java
StringUtils str = new StringUtils();

str.isBlank(null);      // true
str.isBlank("");         // true
str.isBlank("   ");      // true
str.isBlank("oi");       // false
```
