# DateUtils.isFuture(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
boolean isFuture(LocalDate date)
boolean isFuture(LocalDateTime date)
```

## Descrição

Verifica se a data (ou data/hora) é posterior ao momento atual.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` | data a verificar |

## Retorno

`boolean` — `true` se no futuro, `false` caso contrário.

## Comportamento de borda

- **Não existe overload para `Date`**, mesma limitação de [`isPast`](isPast.md) — o Javadoc da
  classe afirma cobertura total de overloads, mas isso **não é verdade** para `isFuture`.
- `isFuture(LocalDate)`: hoje **não** é futuro (`isAfter`, estritamente posterior).
  `isFuture(LocalDateTime)`: granularidade de nanossegundo.
- `date == null` → `NullPointerException`.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.isFuture(LocalDate.now().plusDays(1));  // true
dateUtils.isFuture(LocalDate.now());                // false
```
