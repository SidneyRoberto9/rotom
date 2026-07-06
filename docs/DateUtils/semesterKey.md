# DateUtils.semesterKey(LocalDate date)

**Pacote:** `io.github.sidneyroberto9.rotom.date`
**Arquivo:** `DateUtils.java`

*Introduzido na v1.1.0*.

## Descrição

Retorna uma chave identificando o semestre em que a data cai: `yyyy-01` para janeiro a maio,
`yyyy-06` para junho a dezembro.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `date` | `LocalDate` | data a classificar |

## Retorno

`String` — chave de semestre no formato `yyyy-01` ou `yyyy-06`.

## Comportamento de borda

- `date == null` → `NullPointerException`.
- **Atenção ao limite**: o corte é `month < 6`, então **junho** (mês 6) já cai no segundo grupo
  (`yyyy-06`), tornando os "semestres" desiguais: 5 meses (jan–mai) no primeiro grupo, 7 meses
  (jun–dez) no segundo. Isso é fiel ao `SemesterUtil.currentSemesterKey` original do m4all — não é
  uma divisão de calendário 6+6 meses.
- Não valida `date` além de exigir não-nulo — funciona para qualquer ano.

## Exemplo

```java
DateUtils dateUtils = new DateUtils();

dateUtils.semesterKey(LocalDate.of(2026, 3, 15));  // "2026-01"
dateUtils.semesterKey(LocalDate.of(2026, 6, 1));    // "2026-06" (junho já é 2º grupo)
dateUtils.semesterKey(LocalDate.of(2026, 12, 31));  // "2026-06"
```
