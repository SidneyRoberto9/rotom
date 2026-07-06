# PhoneNumberService.isValid(String phone)

**Pacote:** `io.github.sidneyroberto9.rotom.phoneNumber`
**Arquivo:** `PhoneNumberService.java`

## Descrição

Valida um telefone brasileiro checando comprimento e, quando presente, o DDD contra a lista
`VALID_DDDS` (67 códigos de área reais).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `phone` | `String` | telefone em qualquer formato |

## Retorno

`boolean` — `true` se válido, `false` caso contrário.

## Comportamento de borda

- `phone == null` → `false`.
- **8 ou 9 dígitos** (sem DDD) → aceito **sem** validar DDD (não há DDD para validar).
- **10 ou 11 dígitos** (com DDD) → extrai os 2 primeiros dígitos como DDD e checa contra
  `VALID_DDDS`; se não estiver na lista, `false`. Se tiver 11 dígitos, exige adicionalmente que o
  3º dígito seja `9` (padrão celular).
- Qualquer outro comprimento → `false`.
- Não confundir com `isFormatted` — este método valida **conteúdo/estrutura numérica**, não o
  formato visual (aceita string só com dígitos).

## Exemplo

```java
PhoneNumberService phone = new PhoneNumberService();

phone.isValid("83986635812");   // true (DDD 83 válido, 11 dígitos, 3º = 9)
phone.isValid("00986635812");   // false (DDD 00 não existe)
phone.isValid("98663581");       // true (8 dígitos, sem DDD, aceito sem checagem)
phone.isValid(null);              // false
```
