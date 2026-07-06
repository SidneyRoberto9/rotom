# DateUtils.isToday(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
boolean isToday(LocalDate date)
boolean isToday(LocalDateTime date)
boolean isToday(Date date)
```

## Descrição

Verifica se a data corresponde ao dia de hoje (`LocalDate.now().equals(date)`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data a verificar |

## Retorno

`boolean` — `true` se for hoje, `false` caso contrário.

## Comportamento de borda

- Overload `LocalDateTime` descarta a hora antes de comparar — qualquer horário do dia atual conta
  como "hoje".
- `LocalDate.now()` usa o timezone **padrão do sistema** internamente (JDK), não configurável por
  este método — em ambientes com timezone diferente do esperado, "hoje" pode divergir do horário
  local do usuário.
- `date == null` → `NullPointerException`.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.isToday(LocalDate.now());        // true
dateUtils.isToday(LocalDate.now().plusDays(1));  // false
```
