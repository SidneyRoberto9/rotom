# DurationUtils.hoursBetween(LocalDateTime start, LocalDateTime end)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DurationUtils.java`

## Descrição

Calcula o número de horas inteiras entre dois instantes, via `Duration.between(start,
end).toHours()`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `start` | `LocalDateTime` | início do intervalo |
| `end` | `LocalDateTime` | fim do intervalo |

## Retorno

`long` — horas inteiras entre `start` e `end`.

## Comportamento de borda

- `end` anterior a `start` → valor negativo, sem exceção.
- Trunca minutos/segundos restantes.

## Exemplo

```java
DurationUtils durationUtils = new DurationUtils();

durationUtils.hoursBetween(
    LocalDateTime.of(2026, 1, 1, 10, 0),
    LocalDateTime.of(2026, 1, 1, 15, 30)
);  // 5
```
