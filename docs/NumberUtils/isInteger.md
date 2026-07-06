# NumberUtils.isInteger(String value)

**Pacote:** `io.github.sidneyroberto9.rotom.validation`
**Arquivo:** `NumberUtils.java`

## Descrição

Verifica se a string pode ser convertida para `int` via `Integer.parseInt`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a verificar |

## Retorno

`boolean` — `true` se for um inteiro válido, `false` caso contrário.

## Comportamento de borda

- `value == null` ou `value.isEmpty()` → `false`.
- Valores fora do intervalo de `int` (ex. maiores que `Integer.MAX_VALUE`) → `false`
  (`NumberFormatException` capturada).
- Aceita sinal `+`/`-` e não aceita separadores de milhar nem casas decimais.

## Exemplo

```java
NumberUtils numberUtils = new NumberUtils();

numberUtils.isInteger("42");     // true
numberUtils.isInteger("-42");    // true
numberUtils.isInteger("4.2");    // false
numberUtils.isInteger(null);      // false
```
