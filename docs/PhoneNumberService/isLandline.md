# PhoneNumberService.isLandline(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Verifica se o telefone é um número fixo brasileiro (8 dígitos locais, com ou sem DDD).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | telefone com ou sem DDD |

## Retorno

`boolean` — `true` se fixo, `false` caso contrário.

## Comportamento de borda

- Após `strip`: comprimento **10** (DDD + 8) ou **8** (sem DDD) → `true`. Qualquer outro → `false`.
- `phone == null` → `false`.
- Não checa nenhum prefixo de dígito (diferente de `isMobile`) — só o comprimento.
- Um número de 10 dígitos cujo 3º dígito seja `9` (o que seria inválido para fixo) ainda retorna
  `true` aqui — este método não valida se o padrão é fisicamente coerente, só o tamanho.

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.isLandline("(83) 3222-1234");  // true
phone.isLandline("3222-1234");        // true (sem DDD)
phone.isLandline("(83) 98663-5812");  // false (11 dígitos = celular)
phone.isLandline(null);                // false
```
