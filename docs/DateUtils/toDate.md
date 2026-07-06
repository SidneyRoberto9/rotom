# DateUtils.toDate(date[, zone])

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
Date toDate(LocalDate date)
Date toDate(LocalDate date, ZoneId zone)
Date toDate(LocalDateTime date)
Date toDate(LocalDateTime date, ZoneId zone)
```

## Descrição

Converte `LocalDate` ou `LocalDateTime` para `Date` legado. Os overloads de `LocalDate` usam o
**início do dia** (`atStartOfDay(zone)`) — hora `00:00:00`. Os overloads sem `zone` usam
`ZoneId.systemDefault()`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` | data (e hora, se `LocalDateTime`) a converter |
| `zone` | `ZoneId` | timezone usada na conversão (opcional) |

## Retorno

`Date` — instante correspondente.

## Comportamento de borda

- `toDate(LocalDate)` sempre zera a hora (início do dia) — se precisar preservar um horário
  específico, use o overload `LocalDateTime`.
- `date == null` → lança `NullPointerException` em qualquer overload.
- Usado internamente por [`DateService`](../DateService/README.md) para fazer o round-trip
  `Date` → `LocalDate` → cálculo de dias úteis → `Date` novamente.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.toDate(LocalDate.of(2026, 1, 5));                 // 2026-01-05 00:00:00 (timezone sistema)
dateUtils.toDate(LocalDateTime.of(2026, 1, 5, 14, 30));      // 2026-01-05 14:30:00
dateUtils.toDate(LocalDate.of(2026, 1, 5), ZoneId.of("UTC"));
```
