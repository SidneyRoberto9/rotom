# PhoneNumberService.formatWithCountryCode(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Formata o telefone incluindo o código de país brasileiro `+55`. Remove o prefixo de país quando já
presente no input, para evitar duplicação.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | número com DDD, em qualquer formato |

## Retorno

`String` — telefone formatado com `+55`:
- 11 dígitos → `+55 (XX) XXXXX-XXXX`
- 10 dígitos → `+55 (XX) XXXX-XXXX`

## Comportamento de borda

- **Lança `IllegalArgumentException("Telefone inválido: " + phone)`** se `phone` for `null`.
- **Lança `IllegalArgumentException("Telefone inválido para código de país: " + phone)`** se, após
  strip/remoção do prefixo, o número **não tiver DDD** (8 ou 9 dígitos) — diferente de
  [`format`](format.md), este método **exige DDD** (código de país sem DDD não faz sentido).
- Mesma lógica de detecção de prefixo `55` que `format`: só remove se o total for 12/13 dígitos.

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.formatWithCountryCode("83986635812");     // "+55 (83) 98663-5812"
phone.formatWithCountryCode("5583986635812");   // "+55 (83) 98663-5812" (idempotente)
phone.formatWithCountryCode("98663-5812");       // lança IllegalArgumentException (sem DDD)
phone.formatWithCountryCode(null);                // lança IllegalArgumentException
```
