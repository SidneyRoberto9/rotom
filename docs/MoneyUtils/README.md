# MoneyUtils

Pacote: `io.github.sidneyroberto9.rotom.money`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/money/MoneyUtils.java`

Utilitário para formatação de valores monetários como moeda brasileira (BRL). Introduzido na
v1.1.0.

## Construtor

```java
MoneyUtils money = new MoneyUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `MoneyUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`formatBRL(double value)`](formatBRL.md) | `String` | formata como moeda BRL |
| [`formatBRL(BigDecimal value)`](formatBRL.md) | `String` | formata como moeda BRL |
