# PhoneNumberService.strip(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Remove todos os caracteres não numéricos do telefone (`replaceAll("\\D", "")`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | telefone em qualquer formato |

## Retorno

`String` — apenas dígitos, ou `null` se o input for `null`.

## Comportamento de borda

- `phone == null` → retorna `null`.
- Não valida comprimento nem DDD — é uma limpeza pura, usada internamente por todos os outros
  métodos da classe.

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.strip("(83) 98663-5812");  // "83986635812"
phone.strip(null);                // null
```
