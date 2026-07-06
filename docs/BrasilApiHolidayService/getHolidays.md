# BrasilApiHolidayService.getHolidays(int year)

**Pacote:** `io.github.sidneyroberto9.rotom.date.holiday`
**Arquivo:** `BrasilApiHolidayService.java`

## Descrição

Busca os feriados nacionais brasileiros do ano informado, fazendo `GET
https://brasilapi.com.br/api/feriados/v1/{year}` e extraindo o campo `date` de cada item do array
retornado.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `year` | `int` | ano a consultar (ex. `2026`) |

## Retorno

`Set<LocalDate>` — datas dos feriados nacionais do ano, na ordem retornada pela API.

## Comportamento de borda

- **Lança `IOException`** se a requisição HTTP falhar (rede indisponível, timeout) ou retornar
  status não-2xx — propagada diretamente, **sem fallback silencioso** (diferente de
  [`CepService.lookup`](../CepService/lookup.md), que engole falhas de provider individuais).
- **Chamada de rede em toda invocação** — não há cache. Se for chamar repetidamente para o mesmo
  ano (ex. dentro de um laço de dias úteis), considere armazenar o resultado.
- A BrasilAPI não documenta anos fora de um intervalo suportado; anos muito antigos/futuros podem
  retornar 404, que aqui vira `IOException("HTTP 404 from ...")`.
- Corrige uma limitação do `TimeUtilService` original do m4all: lá, os feriados eram buscados uma
  única vez para o **ano de início** de um cálculo de dias úteis, então um cálculo que atravessasse
  a virada do ano ignorava os feriados do ano seguinte. Este método não tem esse problema porque
  busca exatamente o ano pedido — cabe ao chamador buscar múltiplos anos se o intervalo cruzar
  anos.

## Exemplo

```java
BrasilApiHolidayService holidayService = new BrasilApiHolidayService();

Set<LocalDate> holidays2026 = holidayService.getHolidays(2026);
holidays2026.contains(LocalDate.of(2026, 1, 1));  // true (Ano Novo)

holidayService.getHolidays(99999);  // provavelmente lança IOException (404)
```
