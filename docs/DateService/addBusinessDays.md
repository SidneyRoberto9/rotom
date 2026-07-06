# DateService.addBusinessDays(date, int days)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateService.java`

## Assinaturas

```java
LocalDate addBusinessDays(LocalDate date, int days)
LocalDateTime addBusinessDays(LocalDateTime date, int days)
Date addBusinessDays(Date date, int days)
```

## Descrição

Soma ou subtrai dias úteis à data, pulando fins de semana e feriados nacionais. `days` positivo
avança no tempo; negativo recua.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data de referência |
| `days` | `int` | quantidade de dias úteis a somar (positivo) ou subtrair (negativo) |

## Retorno

Mesmo tipo do parâmetro `date` — nova data após avançar/recuar os dias úteis.

## Comportamento de borda

- `days == 0` → retorna `date` **sem alteração** (early return, sem sequer checar se `date` em si
  já é dia útil).
- Algoritmo incremental: avança/recua **um dia por vez** e só decrementa o contador quando cai em
  dia útil — não pula direto por múltiplos de 7. Para `days` grandes (ex. milhares), é O(days) em
  iterações, não O(1).
- Overload `LocalDateTime` preserva a **hora original** (`atTime(date.toLocalTime())`) — só a parte
  de data é ajustada.
- Overload `Date` faz round-trip via `DateUtils.toLocalDate`/`toDate` usando a `zone` do construtor.

## Exemplo

```java
DateService dateService = new DateService();

// sexta 2026-01-02 + 1 dia útil, pulando fim de semana -> segunda 2026-01-05
dateService.addBusinessDays(LocalDate.of(2026, 1, 2), 1);

dateService.addBusinessDays(LocalDate.of(2026, 1, 5), 0);   // mesma data, sem mudança
dateService.addBusinessDays(LocalDate.of(2026, 1, 5), -3);  // recua 3 dias úteis
```
