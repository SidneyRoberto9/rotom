# NumberUtils.isFloat(String value)

**Pacote:** `io.github.sidneyroberto9.rotom.validation`
**Arquivo:** `NumberUtils.java`

## Descrição

Verifica se a string pode ser convertida para `float` via `Float.parseFloat`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a verificar |

## Retorno

`boolean` — `true` se for um float válido, `false` caso contrário.

## Comportamento de borda

- `value == null` ou `value.isEmpty()` → `false`.
- Aceita notação científica (`"1.5e10"`) e sufixos `f`/`F`/`d`/`D` — herdado do comportamento de
  `Float.parseFloat`, que é mais permissivo do que um simples "número decimal".
- `"NaN"` e `"Infinity"` retornam `true` (são floats válidos do ponto de vista do parser).

## Exemplo

```java
NumberUtils numberUtils = new NumberUtils();

numberUtils.isFloat("4.2");       // true
numberUtils.isFloat("1.5e10");    // true
numberUtils.isFloat("NaN");        // true
numberUtils.isFloat("abc");         // false
```
