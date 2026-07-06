# CNPJService.isValid(String cnpj)

**Pacote:** `io.github.sidneyroberto9.rotom.cnpj`
**Arquivo:** `CNPJService.java`

## Descrição

Valida o CNPJ usando o algoritmo oficial dos dois dígitos verificadores (módulo 11, pesos
`{5,4,3,2,9,8,7,6,5,4,3,2}` e `{6,5,4,3,2,9,8,7,6,5,4,3,2}`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cnpj` | `String` | CNPJ com ou sem máscara |

## Retorno

`boolean` — `true` se matematicamente válido, `false` caso contrário.

## Comportamento de borda

- `cnpj == null` → retorna `false`.
- Após `strip`, comprimento diferente de **14 dígitos** → `false`.
- Rejeita sequências de 14 dígitos repetidos (`(\d)\1{13}`), ex. `"11.111.111/1111-11"` → `false`.
- Regra do dígito verificador: se `sum % 11 < 2`, o dígito é `0`; senão `11 - (sum % 11)`.

## Exemplo

```java
CNPJService cnpj = new CNPJService();

cnpj.isValid("12.345.678/0001-95");   // depende do CNPJ ser matematicamente válido
cnpj.isValid("11.111.111/1111-11");   // false (sequência repetida)
cnpj.isValid(null);                    // false
```
