# NumberUtils.isNumeric(String value)

**Pacote:** `io.github.sidneyroberto9.rotom.validation`
**Arquivo:** `NumberUtils.java`

## Descrição

Verifica se a string representa um número simples (dígitos, sinal opcional, parte decimal
opcional) via regex `-?\d+(\.\d+)?`, sem usar parsing.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a verificar |

## Retorno

`boolean` — `true` se casar com o padrão numérico, `false` caso contrário.

## Comportamento de borda

- `value == null` ou `value.isEmpty()` → `false`.
- **Mais restritivo que [`isDouble`](isDouble.md)/[`isFloat`](isFloat.md)**: não aceita notação
  científica, `"NaN"`, `"Infinity"`, nem sinal `+` explícito (só `-` opcional).
- Não aceita separadores de milhar nem múltiplos pontos decimais.

## Exemplo

```java
NumberUtils numberUtils = new NumberUtils();

numberUtils.isNumeric("42");       // true
numberUtils.isNumeric("-3.14");    // true
numberUtils.isNumeric("1.5e10");   // false (isDouble aceitaria, isNumeric não)
numberUtils.isNumeric("+42");        // false (sinal + não é aceito)
```
