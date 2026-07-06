# PhoneNumberService.format(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Formata o telefone detectando automaticamente celular ou fixo, com ou sem DDD. Remove o prefixo
de código de país (`+55`/`55`) quando presente.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | número em qualquer formato (com/sem DDD, código de país, ou máscara) |

## Retorno

`String` — telefone formatado:
- 11 dígitos → `(XX) XXXXX-XXXX`
- 10 dígitos → `(XX) XXXX-XXXX`
- 9 dígitos → `XXXXX-XXXX`
- 8 dígitos → `XXXX-XXXX`

## Comportamento de borda

- **Lança `IllegalArgumentException("Telefone inválido: " + phone)`** se `phone` for `null` ou se,
  após remover o prefixo de país, o comprimento não for 8, 9, 10 ou 11 dígitos.
- Detecta prefixo de país apenas quando os dígitos começam com `"55"` **e** o total for 12 ou 13
  dígitos (DDI + DDD + número). Não remove `"55"` se o total já bater com 10/11 (evita confundir
  DDD 55 — Rio Grande do Sul — com o DDI).
- Não valida DDD nem dígitos verificadores de celular — apenas formata pelo comprimento.

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.format("83986635812");        // "(83) 98663-5812"
phone.format("5583986635812");      // "(83) 98663-5812" (remove +55)
phone.format("8332221234");          // "(83) 3222-1234"
phone.format("98663-5812");          // "98663-5812" (sem DDD)
phone.format(null);                   // lança IllegalArgumentException
phone.format("123");                  // lança IllegalArgumentException
```
