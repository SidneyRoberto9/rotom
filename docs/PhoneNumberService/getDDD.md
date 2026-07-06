# PhoneNumberService.getDDD(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Extrai os 2 dígitos do código de área (DDD) do telefone. Aplica [`strip`](strip.md) internamente.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | telefone com DDD |

## Retorno

`String` — DDD como string de 2 dígitos, ou `null` se o número tiver menos de 10 dígitos (sem DDD).

## Comportamento de borda

- Requer **no mínimo 10 dígitos** (DDD + número local de 8) após `strip`; caso contrário `null`.
- Não valida se o DDD extraído está na lista `VALID_DDDS` — apenas retorna os 2 primeiros dígitos,
  mesmo que não correspondam a um DDD real. Para validar, combine com [`isValid`](isValid.md).
- `phone == null` → `null` (via `strip` retornando `null`).

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.getDDD("(83) 98663-5812");  // "83"
phone.getDDD("98663-5812");        // null (só 9 dígitos, sem DDD)
phone.getDDD(null);                 // null
```
