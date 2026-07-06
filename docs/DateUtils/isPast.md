# DateUtils.isPast(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
boolean isPast(LocalDate date)
boolean isPast(LocalDateTime date)
```

## Descrição

Verifica se a data (ou data/hora) é anterior ao momento atual.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` | data a verificar |

## Retorno

`boolean` — `true` se no passado, `false` caso contrário.

## Comportamento de borda

- **Não existe overload para `Date`** — diferente da maioria dos métodos desta classe. Para
  checar um `Date`, converta antes com [`toLocalDate`](toLocalDate.md) ou
  [`toLocalDateTime`](toLocalDateTime.md). Isso contradiz o Javadoc da classe, que afirma que
  "todos os métodos são sobrecarregados para `LocalDate`, `LocalDateTime` e `Date`" — **essa
  afirmação está incorreta para este método**.
- `isPast(LocalDate)` compara com `LocalDate.now()` (granularidade de dia — hoje **não** é
  passado). `isPast(LocalDateTime)` compara com `LocalDateTime.now()` (granularidade de
  nanossegundo — quase qualquer `LocalDateTime` já construído estará "no passado" no momento da
  chamada).
- `date == null` → `NullPointerException`.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.isPast(LocalDate.now().minusDays(1));  // true
dateUtils.isPast(LocalDate.now());                 // false (hoje não é passado)
dateUtils.isPast(LocalDateTime.now().minusSeconds(1));  // true
```
