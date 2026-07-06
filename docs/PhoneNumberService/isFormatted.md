# PhoneNumberService.isFormatted(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Verifica se o telefone já está formatado com DDD e hífen, seja como celular `(XX) XXXXX-XXXX` ou
fixo `(XX) XXXX-XXXX`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | telefone a verificar |

## Retorno

`boolean` — `true` se já formatado (celular ou fixo), `false` caso contrário.

## Comportamento de borda

- `phone == null` → `false`.
- Usa `matches()` (casamento completo) — sem espaços/caracteres extras é obrigatório casar
  exatamente `(XX) 9XXXX-XXXX` ou `(XX) XXXX-XXXX`.
- **Não aceita formato com código de país** (`+55 (XX) ...`) — `formatWithCountryCode` produz uma
  string que este método classificaria como não formatada.

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.isFormatted("(83) 98663-5812");     // true (celular)
phone.isFormatted("(83) 3222-1234");      // true (fixo)
phone.isFormatted("83986635812");          // false
phone.isFormatted("+55 (83) 98663-5812"); // false (não cobre código de país)
```
