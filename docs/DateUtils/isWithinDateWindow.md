# DateUtils.isWithinDateWindow(date, int startDay, int endDay)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
boolean isWithinDateWindow(LocalDate date, int startDay, int endDay)
boolean isWithinDateWindow(LocalDateTime date, int startDay, int endDay)
boolean isWithinDateWindow(Date date, int startDay, int endDay)
```

## Descrição

Verifica se o dia do mês da data está dentro da janela definida por `startDay` e `endDay`,
inclusive nos dois extremos. Útil para regras como "vencimento entre o dia 5 e o dia 10".

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data a verificar |
| `startDay` | `int` | dia inicial da janela (1–31) |
| `endDay` | `int` | dia final da janela (1–31) |

## Retorno

`boolean` — `true` se o dia do mês estiver entre `startDay` e `endDay` (inclusive).

## Comportamento de borda

- **Não valida** que `startDay <= endDay` nem que estão no intervalo `1`–`31` — se `startDay >
  endDay`, a comparação `day >= startDay && day <= endDay` nunca será satisfeita (retorna sempre
  `false`), sem erro explícito.
- Não considera o mês/ano da data — só o dia numérico (`getDayOfMonth()`). Uma data de fevereiro
  com dia 30 é impossível de existir no JDK, então não há problema aqui, mas a janela é puramente
  sobre o número do dia, ignorando quantos dias o mês realmente tem.
- Overload `Date` usa timezone padrão do sistema (via `toLocalDate(date)` sem `zone`).

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.isWithinDateWindow(LocalDate.of(2026, 3, 7), 5, 10);   // true (dia 7 está em [5,10])
dateUtils.isWithinDateWindow(LocalDate.of(2026, 3, 12), 5, 10);  // false
```
