# DurationUtils.daysBetween(LocalDateTime start, LocalDateTime end)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DurationUtils.java`

## Descrição

Calcula o número de dias inteiros (24h) entre dois instantes, via `Duration.between(start,
end).toDays()`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `start` | `LocalDateTime` | início do intervalo |
| `end` | `LocalDateTime` | fim do intervalo |

## Retorno

`long` — dias inteiros entre `start` e `end`.

## Comportamento de borda

- Se `end` for anterior a `start`, retorna um valor **negativo** (não lança exceção — `Duration`
  aceita intervalos invertidos).
- Trunca, não arredonda: `23h59min` de diferença retorna `0`.
- `start`/`end` nulos → `NullPointerException`.

## Exemplo

```java
DurationUtils durationUtils = new DurationUtils();

durationUtils.daysBetween(
    LocalDateTime.of(2026, 1, 1, 0, 0),
    LocalDateTime.of(2026, 1, 3, 12, 0)
);  // 2
```
