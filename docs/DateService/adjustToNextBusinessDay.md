# DateService.adjustToNextBusinessDay(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Assinaturas

```java
LocalDate adjustToNextBusinessDay(LocalDate date)
LocalDateTime adjustToNextBusinessDay(LocalDateTime date)
Date adjustToNextBusinessDay(Date date)
```

## Descrição

Avança a data para o próximo dia útil, caso ainda não seja um. Se a data já for dia útil, é
retornada **sem alteração**.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data de referência |

## Retorno

Mesmo tipo do parâmetro `date` — a própria data ou o próximo dia útil.

## Comportamento de borda

- Loop `while (!isBusinessDay(result)) result = result.plusDays(1);` — sem limite de iterações;
  teoricamente poderia rodar indefinidamente se `isBusinessDay` sempre retornasse `false` (não
  ocorre na prática, pois nenhum calendário tem feriados/fins-de-semana consecutivos infinitos).
- Base de [`getFirstBusinessDayOfMonth`](getFirstBusinessDayOfMonth.md).
- Overload `LocalDateTime` preserva a hora original.

## Exemplo

```java
DateService dateService = new DateService();

// 2026-01-01 é feriado (Ano Novo, quinta) -> avança para 2026-01-02 (sexta, se não feriado)
dateService.adjustToNextBusinessDay(LocalDate.of(2026, 1, 1));

// já é dia útil -> retorna a mesma data
dateService.adjustToNextBusinessDay(LocalDate.of(2026, 1, 5));
```
