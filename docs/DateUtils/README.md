# DateUtils

Pacote: `io.github.sidneyroberto9.rotom.date`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/date/DateUtils.java`

Classe utilitária para conversões, checagens temporais e formatação de datas. **Não** cobre dias
úteis/feriados (veja [`DateService`](../DateService/README.md) para isso).

> A afirmação de que "todos os métodos são sobrecarregados para `LocalDate`, `LocalDateTime` e
> `Date`" (presente no Javadoc da classe) **não é totalmente verdadeira** — `isPast`/`isFuture` não
> têm overload `Date`, e `formatDateTime` não tem overload `LocalDate`. Ver detalhes em cada
> método.

## Construtor

```java
DateUtils dateUtils = new DateUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `DateUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Overloads |
|---|---|---|
| [`toLocalDate(date[, zone])`](toLocalDate.md) | `LocalDate` | `Date`, `Date+ZoneId` |
| [`toLocalDateTime(date[, zone])`](toLocalDateTime.md) | `LocalDateTime` | `Date`, `Date+ZoneId` |
| [`toDate(date[, zone])`](toDate.md) | `Date` | `LocalDate`, `LocalDate+ZoneId`, `LocalDateTime`, `LocalDateTime+ZoneId` |
| [`isWeekend(date)`](isWeekend.md) | `boolean` | `LocalDate`, `LocalDateTime`, `Date` |
| [`isWeekDay(date)`](isWeekDay.md) | `boolean` | `LocalDate`, `LocalDateTime`, `Date` |
| [`isToday(date)`](isToday.md) | `boolean` | `LocalDate`, `LocalDateTime`, `Date` |
| [`isPast(date)`](isPast.md) | `boolean` | `LocalDate`, `LocalDateTime` **(sem `Date`)** |
| [`isFuture(date)`](isFuture.md) | `boolean` | `LocalDate`, `LocalDateTime` **(sem `Date`)** |
| [`isWithinDateWindow(date, startDay, endDay)`](isWithinDateWindow.md) | `boolean` | `LocalDate`, `LocalDateTime`, `Date` |
| [`formatDate(date)`](formatDate.md) | `String` | `LocalDate`, `LocalDateTime`, `Date` |
| [`formatDateTime(date)`](formatDateTime.md) | `String` | `LocalDateTime`, `Date` **(sem `LocalDate`)** |
| [`formatMonthYear(month, year)`](formatMonthYear.md) | `String` | — |
| [`semesterKey(LocalDate date)`](semesterKey.md) | `String` | — (introduzido v1.1.0) |
