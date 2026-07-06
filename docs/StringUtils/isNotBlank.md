# StringUtils.isNotBlank(String value)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Verifica se a string tem conteúdo relevante (não é null, vazia, ou blank). É a negação exata de
[`isBlank`](isBlank.md).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a verificar |

## Retorno

`boolean` — `true` se tiver conteúdo, `false` caso contrário.

## Comportamento de borda

Sem particularidades — implementado como `!this.isBlank(value)`.

## Exemplo

```java
StringUtils str = new StringUtils();

str.isNotBlank("oi");    // true
str.isNotBlank("   ");   // false
str.isNotBlank(null);     // false
```
