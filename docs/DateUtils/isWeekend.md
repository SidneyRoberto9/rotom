# DateUtils.isWeekend(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
boolean isWeekend(LocalDate date)
boolean isWeekend(LocalDateTime date)
boolean isWeekend(Date date)
```

## Descrição

Verifica se a data cai em fim de semana (sábado ou domingo).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data a verificar |

## Retorno

`boolean` — `true` se sábado ou domingo, `false` caso contrário.

## Comportamento de borda

- Overload `Date` usa `toLocalDate(date)` **sem** timezone explícito — sempre timezone padrão do
  sistema, mesmo que o `DateService` que o invoca tenha sido construído com uma `zone`
  customizada. Se precisar de timezone controlado, converta manualmente com
  [`toLocalDate(date, zone)`](toLocalDate.md) e chame o overload `LocalDate`.
- `date == null` → `NullPointerException` propagada da conversão.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.isWeekend(LocalDate.of(2026, 1, 3));  // true (sábado)
dateUtils.isWeekend(LocalDate.of(2026, 1, 5));  // false (segunda)
```
