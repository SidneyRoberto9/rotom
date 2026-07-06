# DateService.subtractBusinessDays(date, int days)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Assinaturas

```java
LocalDate subtractBusinessDays(LocalDate date, int days)
LocalDateTime subtractBusinessDays(LocalDateTime date, int days)
Date subtractBusinessDays(Date date, int days)
```

## Descrição

Subtrai dias úteis da data, pulando fins de semana e feriados. Delega para
[`addBusinessDays(date, -days)`](addBusinessDays.md).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data de referência |
| `days` | `int` | quantidade de dias úteis a subtrair (deve ser ≥ 0) |

## Retorno

Mesmo tipo do parâmetro `date` — nova data recuada.

## Comportamento de borda

- **Lança `IllegalArgumentException("days deve ser maior ou igual a zero")`** se `days < 0` —
  diferente de `addBusinessDays`, que aceita negativos livremente. Aqui a negatividade é
  bloqueada porque a intenção semântica do método já é "subtrair".
- Overload `LocalDateTime` preserva a hora original.
- Overload `Date` faz round-trip pela `zone` do construtor.

## Exemplo

```java
DateService dateService = new DateService();

dateService.subtractBusinessDays(LocalDate.of(2026, 1, 6), 3);  // recua 3 dias úteis
dateService.subtractBusinessDays(LocalDate.of(2026, 1, 6), -1); // lança IllegalArgumentException
```
