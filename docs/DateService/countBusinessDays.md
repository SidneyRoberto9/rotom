# DateService.countBusinessDays(LocalDate from, LocalDate to)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Descrição

Conta o número de dias úteis no intervalo de `from` (**inclusive**) até `to` (**exclusive**).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `from` | `LocalDate` | data inicial, inclusive |
| `to` | `LocalDate` | data final, exclusive |

## Retorno

`long` — quantidade de dias úteis no intervalo `[from, to)`.

## Comportamento de borda

- **Único overload** — só existe para `LocalDate` (não há `LocalDateTime`/`Date`).
- **Lança `IllegalArgumentException("'to' deve ser igual ou posterior a 'from'")`** se `to` for
  anterior a `from`.
- `from == to` → retorna `0` (intervalo vazio, já que `to` é exclusive).
- Itera dia a dia (`current.plusDays(1)`) — O(dias no intervalo), não há atalho matemático.

## Exemplo

```java
DateService dateService = new DateService();

// conta dias úteis de janeiro/2026 (1º inclusive, 1º de fevereiro exclusive)
dateService.countBusinessDays(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 2, 1));

dateService.countBusinessDays(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 1));  // 0

dateService.countBusinessDays(LocalDate.of(2026, 2, 1), LocalDate.of(2026, 1, 1));
// lança IllegalArgumentException ('to' antes de 'from')
```
