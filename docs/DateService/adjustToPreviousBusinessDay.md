# DateService.adjustToPreviousBusinessDay(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Assinaturas

```java
LocalDate adjustToPreviousBusinessDay(LocalDate date)
LocalDateTime adjustToPreviousBusinessDay(LocalDateTime date)
Date adjustToPreviousBusinessDay(Date date)
```

## Descrição

Recua a data para o dia útil anterior, caso ainda não seja um. Se a data já for dia útil, é
retornada **sem alteração**.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data de referência |

## Retorno

Mesmo tipo do parâmetro `date` — a própria data ou o dia útil anterior.

## Comportamento de borda

- Loop `while (!isBusinessDay(result)) result = result.minusDays(1);` — mesma observação de
  ausência de limite que [`adjustToNextBusinessDay`](adjustToNextBusinessDay.md).
- Base de [`getLastBusinessDayOfMonth`](getLastBusinessDayOfMonth.md).
- Overload `LocalDateTime` preserva a hora original.

## Exemplo

```java
DateService dateService = new DateService();

// domingo -> recua para sexta útil anterior
dateService.adjustToPreviousBusinessDay(LocalDate.of(2026, 1, 4));

dateService.adjustToPreviousBusinessDay(LocalDate.of(2026, 1, 5));  // já útil, sem mudança
```
