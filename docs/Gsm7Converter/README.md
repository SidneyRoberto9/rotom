# Gsm7Converter

Pacote: `io.github.sidneyroberto9.rotom.sms`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/sms/Gsm7Converter.java`

Utilitário para converter texto UTF-8 para o alfabeto padrão GSM 7-bit usado em corpos de SMS.
Introduzido na v1.1.0 a partir de `Utf8ToGsm7ConverterService`, duplicado em 8+ projetos do m4all
(ver [`docs/AUDIT.md`](../AUDIT.md)).

## Construtor

```java
Gsm7Converter gsm7 = new Gsm7Converter();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `Gsm7Converter` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`convertToGsm7(String input)`](convertToGsm7.md) | `String` | restringe o texto ao charset GSM-7 |
