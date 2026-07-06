# DateUtils.formatDateTime(date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

## Assinaturas

```java
String formatDateTime(LocalDateTime date)
String formatDateTime(Date date)
```

## Descrição

Formata data e hora como `dd/MM/yyyy HH:mm`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDateTime` \| `Date` | data e hora a formatar |

## Retorno

`String` — data/hora formatada, ex. `"05/01/2026 14:30"`.

## Comportamento de borda

- **Não existe overload para `LocalDate`** — faz sentido semanticamente (`LocalDate` não tem
  componente de hora), mas contraria o Javadoc geral da classe que promete overloads para os 3
  tipos em todos os métodos. Se você só tem um `LocalDate` e precisa de uma string com horário,
  use [`formatDate`](formatDate.md) em vez deste método.
- Overload `Date` usa timezone padrão do sistema (via `toLocalDateTime(date)` sem `zone`).
- `date == null` → `NullPointerException`.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.formatDateTime(LocalDateTime.of(2026, 1, 5, 14, 30));  // "05/01/2026 14:30"
dateUtils.formatDateTime(new Date());
```
