# PhoneNumberService.isMobile(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Verifica se o telefone é um número de celular brasileiro: celular tem 9 dígitos locais e o
primeiro dígito local é `9`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | telefone com ou sem DDD |

## Retorno

`boolean` — `true` se celular, `false` caso contrário.

## Comportamento de borda

- Após `strip`: **11 dígitos** → celular se o 3º dígito (índice 2, após o DDD) for `9`.
  **9 dígitos** (sem DDD) → celular se o 1º dígito for `9`.
- Qualquer outro comprimento → `false`.
- `phone == null` → `false`.
- Não valida o DDD nem os demais dígitos — só checa comprimento + prefixo `9`.

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.isMobile("(83) 98663-5812");  // true  (11 dígitos, 3º = 9)
phone.isMobile("98663-5812");        // true  (9 dígitos, 1º = 9)
phone.isMobile("(83) 3222-1234");    // false (fixo)
phone.isMobile(null);                 // false
```
