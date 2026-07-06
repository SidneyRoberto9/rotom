# DateUtils.formatMonthYear(int month, int year)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Descrição

Formata mês e ano como `MM/yyyy`, com o mês sempre em 2 dígitos.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `month` | `int` | mês (1 = janeiro, 12 = dezembro) |
| `year` | `int` | ano (ex. `2026`) |

## Retorno

`String` — mês e ano formatados, ex. `"01/2026"`.

## Comportamento de borda

- **Único método da classe que não recebe nenhum tipo de data** — recebe `int`/`int` soltos, sem
  validação de intervalo. `month = 13` ou `month = 0` são aceitos e formatados literalmente
  (`String.format("%02d/%d", ...)`), sem lançar exceção.
- `year` não é forçado a 4 dígitos — anos com 1–3 dígitos aparecem sem zero à esquerda (ex.
  `year=5` → `"01/5"`).

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.formatMonthYear(1, 2026);   // "01/2026"
dateUtils.formatMonthYear(13, 2026);  // "13/2026" (mês inválido, sem validação)
```
