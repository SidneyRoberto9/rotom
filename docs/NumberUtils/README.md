# NumberUtils

Pacote: `io.github.sidneyroberto9.rotom.validation`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/validation/NumberUtils.java`

Utilitário para verificar se uma string representa um número válido. Introduzido na v1.1.0.

## Construtor

```java
NumberUtils numberUtils = new NumberUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `NumberUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`isInteger(String value)`](isInteger.md) | `boolean` | verifica se pode ser parseado como `int` |
| [`isFloat(String value)`](isFloat.md) | `boolean` | verifica se pode ser parseado como `float` |
| [`isDouble(String value)`](isDouble.md) | `boolean` | verifica se pode ser parseado como `double` |
| [`isNumeric(String value)`](isNumeric.md) | `boolean` | verifica padrão numérico simples via regex |
