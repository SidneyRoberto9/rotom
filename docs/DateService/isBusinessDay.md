# DateService.isBusinessDay(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Assinaturas

```java
boolean isBusinessDay(LocalDate date)
boolean isBusinessDay(LocalDateTime date)
boolean isBusinessDay(Date date)
```

## Descrição

Verifica se a data é dia útil: **não** é fim de semana **e** não é feriado nacional
(`dateUtils.isWeekDay(date) && !isHoliday(date)`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data a verificar |

## Retorno

`boolean` — `true` se dia útil, `false` se fim de semana ou feriado.

## Comportamento de borda

- Combina [`DateUtils.isWeekDay`](../DateUtils/isWeekDay.md) e [`isHoliday`](isHoliday.md) — não
  reimplementa a checagem de fim de semana.
- Base de todos os outros cálculos de dias úteis da classe (`addBusinessDays`,
  `adjustToNextBusinessDay`, etc).

## Exemplo

```java
DateService dateService = new DateService();

dateService.isBusinessDay(LocalDate.of(2026, 1, 1));  // false (feriado, Ano Novo)
dateService.isBusinessDay(LocalDate.of(2026, 1, 3));  // false (sábado)
dateService.isBusinessDay(LocalDate.of(2026, 1, 5));  // true (segunda-feira útil)
```
