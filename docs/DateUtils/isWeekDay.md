# DateUtils.isWeekDay(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
boolean isWeekDay(LocalDate date)
boolean isWeekDay(LocalDateTime date)
boolean isWeekDay(Date date)
```

## Descrição

Verifica se a data cai em dia de semana (segunda a sexta). É a negação exata de
[`isWeekend`](isWeekend.md).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data a verificar |

## Retorno

`boolean` — `true` se dia de semana, `false` se fim de semana.

## Comportamento de borda

- **Não considera feriados** — um feriado numa terça-feira ainda retorna `true` aqui. Para incluir
  feriados, use [`DateService.isBusinessDay`](../DateService/isBusinessDay.md).
- Mesma ressalva de timezone do overload `Date` de `isWeekend`.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.isWeekDay(LocalDate.of(2026, 1, 5));  // true (segunda)
dateUtils.isWeekDay(LocalDate.of(2026, 1, 1));  // true (feriado, mas não é fim de semana!)
```
