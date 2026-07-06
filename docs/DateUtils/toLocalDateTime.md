# DateUtils.toLocalDateTime(date[, zone])

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
LocalDateTime toLocalDateTime(Date date)
LocalDateTime toLocalDateTime(Date date, ZoneId zone)
```

## Descrição

Converte um `Date` legado para `LocalDateTime` (preservando hora/minuto/segundo). O overload sem
`zone` usa `ZoneId.systemDefault()`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `Date` | data/hora a converter |
| `zone` | `ZoneId` | timezone usada na conversão (opcional) |

## Retorno

`LocalDateTime` — data e hora convertidas.

## Comportamento de borda

- `date == null` → lança `NullPointerException` (mesmo padrão de [`toLocalDate`](toLocalDate.md)).
- Mesma ressalva de dependência do timezone do sistema quando `zone` não é informado.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.toLocalDateTime(new Date());
dateUtils.toLocalDateTime(new Date(), ZoneId.of("UTC"));
```
