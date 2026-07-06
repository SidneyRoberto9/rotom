# DateService.getLastBusinessDayOfMonth(int year, int month)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Descrição

Retorna o último dia útil do mês informado. Aplica
[`adjustToPreviousBusinessDay`](adjustToPreviousBusinessDay.md) sobre o último dia do mês
(`YearMonth.atEndOfMonth()`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `year` | `int` | ano (ex. `2026`) |
| `month` | `int` | mês (1 = janeiro, 12 = dezembro) |

## Retorno

`LocalDate` — último dia útil do mês.

## Comportamento de borda

- **Único overload** — só existe para `LocalDate`.
- `month` inválido propaga `DateTimeException` de `YearMonth.of`.
- Lida corretamente com meses de 28/29/30/31 dias (delegado ao `atEndOfMonth()` do JDK).

## Exemplo

```java
DateService dateService = new DateService();

dateService.getLastBusinessDayOfMonth(2026, 2);  // último dia útil de fevereiro/2026
```
