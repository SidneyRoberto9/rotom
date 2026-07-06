# DateService.getFirstBusinessDayOfMonth(int year, int month)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Descrição

Retorna o primeiro dia útil do mês informado. Aplica
[`adjustToNextBusinessDay`](adjustToNextBusinessDay.md) sobre o dia 1 do mês.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `year` | `int` | ano (ex. `2026`) |
| `month` | `int` | mês (1 = janeiro, 12 = dezembro) |

## Retorno

`LocalDate` — primeiro dia útil do mês.

## Comportamento de borda

- **Único overload** — só existe para `LocalDate` (não há versão `LocalDateTime`/`Date`).
- `month` fora do intervalo `1`–`12` propaga a exceção de `YearMonth.of(year, month)`
  (`DateTimeException`), sem tratamento adicional na lib.
- Se o dia 1 já for útil, ele mesmo é retornado (comportamento herdado de
  `adjustToNextBusinessDay`).

## Exemplo

```java
DateService dateService = new DateService();

dateService.getFirstBusinessDayOfMonth(2026, 1);  // 1º dia útil de janeiro/2026
```
