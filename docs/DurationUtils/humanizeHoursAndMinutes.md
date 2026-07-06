# DurationUtils.humanizeHoursAndMinutes(LocalDateTime start, LocalDateTime end)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DurationUtils.java`

## Descrição

Descreve a duração entre dois instantes em horas e minutos, em português.
Exemplo: `"2 hora(s) e 15 minuto(s)"`, ou `"45 minutos"` quando o total é menor que 1 hora.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `start` | `LocalDateTime` | início do intervalo |
| `end` | `LocalDateTime` | fim do intervalo |

## Retorno

`String` — descrição legível da duração.

## Comportamento de borda

- **Limite corrigido**: minutos `>= 60` já viram horas (`"1 hora(s) e 0 minuto(s)"` em vez de
  `"60 minutos"`, que era o comportamento do `TimeDifference.calculateHoursAndMinutesDifference`
  original, cujo teste era `> 60`, não `>= 60`).
- `end` anterior a `start` → minutos/horas negativos aparecem na string sem tratamento especial
  (ex. `"-1 hora(s) e 0 minuto(s)"`).
- Não pluraliza de fato — usa sempre `"hora(s)"`/`"minuto(s)"` (com parênteses literais), mesmo
  para `1 hora`.

## Exemplo

```java
DurationUtils durationUtils = new DurationUtils();

durationUtils.humanizeHoursAndMinutes(
    LocalDateTime.of(2026, 1, 1, 10, 0),
    LocalDateTime.of(2026, 1, 1, 10, 45)
);  // "45 minutos"

durationUtils.humanizeHoursAndMinutes(
    LocalDateTime.of(2026, 1, 1, 10, 0),
    LocalDateTime.of(2026, 1, 1, 11, 0)
);  // "1 hora(s) e 0 minuto(s)" (60 min = 1h, corrigido)
```
