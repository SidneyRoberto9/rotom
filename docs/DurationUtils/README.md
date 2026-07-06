# DurationUtils

Pacote: `io.github.sidneyroberto9.rotom.date`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/date/DurationUtils.java`

Utilitário para calcular e descrever a duração entre dois instantes, com saída legível em
português. Introduzido na v1.1.0. Os limites de corte são `>= 60` minutos e `>= 24` horas, de
modo que exatamente 60 minutos vira `"1 hora(s) e 0 minuto(s)"` em vez de `"60 minutos"`.

## Construtor

```java
DurationUtils durationUtils = new DurationUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `DurationUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`daysBetween(LocalDateTime start, LocalDateTime end)`](daysBetween.md) | `long` | dias inteiros entre dois instantes |
| [`hoursBetween(LocalDateTime start, LocalDateTime end)`](hoursBetween.md) | `long` | horas inteiras entre dois instantes |
| [`humanizeHoursAndMinutes(LocalDateTime start, LocalDateTime end)`](humanizeHoursAndMinutes.md) | `String` | descrição em horas e minutos |
| [`humanizeDaysHoursAndMinutes(LocalDateTime start, LocalDateTime end)`](humanizeDaysHoursAndMinutes.md) | `String` | descrição em dias, horas e minutos |
