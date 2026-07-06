# DateUtils.toLocalDate(date[, zone])

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
LocalDate toLocalDate(Date date)
LocalDate toLocalDate(Date date, ZoneId zone)
```

## Descrição

Converte um `Date` legado para `LocalDate`. O overload sem `zone` usa o timezone padrão do
sistema (`ZoneId.systemDefault()`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `Date` | data a converter |
| `zone` | `ZoneId` | timezone usada na conversão (opcional) |

## Retorno

`LocalDate` — data convertida.

## Comportamento de borda

- `date == null` → **lança `NullPointerException`** (`date.toInstant()` sobre `null`), sem
  tratamento na lib.
- O overload de 1 parâmetro delega para o de 2, usando `ZoneId.systemDefault()` — se a JVM rodar
  com timezone diferente do esperado (ex. `UTC` em produção vs `America/Sao_Paulo` em dev), o
  resultado pode variar entre ambientes. Para reprodutibilidade, prefira sempre passar `zone`
  explicitamente.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.toLocalDate(new Date());                          // usa timezone do sistema
dateUtils.toLocalDate(new Date(), ZoneId.of("America/Sao_Paulo"));
```
