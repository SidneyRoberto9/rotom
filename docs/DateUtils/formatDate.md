# DateUtils.formatDate(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
String formatDate(LocalDate date)
String formatDate(LocalDateTime date)
String formatDate(Date date)
```

## Descrição

Formata a data como `dd/MM/yyyy`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` \| `LocalDateTime` \| `Date` | data a formatar |

## Retorno

`String` — data formatada, ex. `"05/01/2026"`.

## Comportamento de borda

- Overload `LocalDateTime` **ignora a hora** — só formata a parte de data.
- Overload `Date` usa timezone padrão do sistema (via `toLocalDate(date)` sem `zone`).
- `date == null` → `NullPointerException`.
- Padrão fixo `dd/MM/yyyy` (`DateTimeFormatter`), sem suporte a customização de locale/formato
  neste método.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.formatDate(LocalDate.of(2026, 1, 5));           // "05/01/2026"
dateUtils.formatDate(LocalDateTime.of(2026, 1, 5, 14, 30)); // "05/01/2026" (hora ignorada)
```
