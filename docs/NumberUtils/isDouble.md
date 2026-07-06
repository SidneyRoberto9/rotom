# NumberUtils.isDouble(String value)

**Pacote:** `io.github.sidneyroberto9.rotom.validation`
**Arquivo:** `NumberUtils.java`

## Descrição

Verifica se a string pode ser convertida para `double` via `Double.parseDouble`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a verificar |

## Retorno

`boolean` — `true` se for um double válido, `false` caso contrário.

## Comportamento de borda

Mesmas ressalvas de [`isFloat`](isFloat.md): aceita notação científica, `"NaN"`/`"Infinity"`, e
sufixos numéricos válidos para `double`.

## Exemplo

```java
NumberUtils numberUtils = new NumberUtils();

numberUtils.isDouble("3.14159265358979");  // true
numberUtils.isDouble(null);                  // false
```
