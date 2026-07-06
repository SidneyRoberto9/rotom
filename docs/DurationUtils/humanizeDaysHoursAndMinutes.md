# DurationUtils.humanizeDaysHoursAndMinutes(LocalDateTime start, LocalDateTime end)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DurationUtils.java`

## Descrição

Descreve a duração entre dois instantes em dias, horas e minutos, em português.
Exemplo: `"1 dia(s) 3 hora(s) e 20 minuto(s)"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `start` | `LocalDateTime` | início do intervalo |
| `end` | `LocalDateTime` | fim do intervalo |

## Retorno

`String` — descrição legível da duração, com granularidade decrescente:
- `0` dias e `0` horas → `"{m} minutos"`
- `0` dias e horas > 0 → `"{h} hora(s) e {m} minuto(s)"`
- dias > 0 → `"{d} dia(s) {h} hora(s) e {m} minuto(s)"`

## Comportamento de borda

- **Limites corrigidos**: minutos `>= 60` viram horas, e horas `>= 24` viram dias (o
  `TimeDifference.calculateDaysHoursAndMinutesDifference` original usava `> 60`/`> 24`, então
  exatamente 24 horas ficava presa na faixa de horas em vez de virar `"1 dia(s)"`).
- `end` anterior a `start` → valores negativos sem tratamento especial.

## Exemplo

```java
DurationUtils durationUtils = new DurationUtils();

durationUtils.humanizeDaysHoursAndMinutes(
    LocalDateTime.of(2026, 1, 1, 8, 0),
    LocalDateTime.of(2026, 1, 2, 11, 20)
);  // "1 dia(s) 3 hora(s) e 20 minuto(s)"

durationUtils.humanizeDaysHoursAndMinutes(
    LocalDateTime.of(2026, 1, 1, 0, 0),
    LocalDateTime.of(2026, 1, 2, 0, 0)
);  // "1 dia(s) 0 hora(s) e 0 minuto(s)" (24h = 1 dia, corrigido)
```
