# BrasilApiHolidayService

Pacote: `io.github.sidneyroberto9.rotom.date.holiday`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/date/holiday/BrasilApiHolidayService.java`

Serviço para buscar feriados nacionais brasileiros de um ano específico via a
[BrasilAPI](https://brasilapi.com.br/docs#tag/Feriados-Nacionais) pública. Introduzido na v1.1.0.

> Diferente de [`DateService`](../DateService/README.md), que usa o calendário offline **jollyday**
> (sem chamadas de rede), este serviço faz uma **chamada HTTP em tempo real** a cada invocação e
> reflete os dados da BrasilAPI tal como estão — sem cache interno.

**Reimplementação enxuta**: usa **okhttp + jackson** (dependências já existentes na lib, mesmo
padrão usado pelos providers de CEP), em vez de `spring-web`/`RestTemplate` como o original.

## Construtor

```java
BrasilApiHolidayService holidayService = new BrasilApiHolidayService();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `BrasilApiHolidayService` fica disponível para
injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`getHolidays(int year)`](getHolidays.md) | `Set<LocalDate>` | busca os feriados nacionais do ano informado |
