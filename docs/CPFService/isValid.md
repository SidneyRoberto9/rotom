# CPFService.isValid(String cpf)

**Pacote:** `io.github.sidneyroberto9.rotom.cpf`
**Arquivo:** `CPFService.java`

## Descrição

Valida o CPF usando o algoritmo oficial de dígitos verificadores (módulo 11, dois dígitos).
Aceita CPF com ou sem máscara (aplica [`strip`](strip.md) internamente).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cpf` | `String` | CPF com ou sem máscara |

## Retorno

`boolean` — `true` se matematicamente válido, `false` caso contrário.

## Comportamento de borda

- `cpf == null` → retorna `false`.
- Após `strip`, se o comprimento não for exatamente **11 dígitos** → `false`.
- Rejeita sequências de 11 dígitos repetidos (regex `(\d)\1{10}`), ex. `"111.111.111-11"` → `false`,
  mesmo que passasse no cálculo dos dígitos verificadores.
- Calcula dois dígitos verificadores com pesos decrescentes (10→2 e 11→2); se `sum % 11 > 9` o
  dígito é ajustado para `0`.

## Exemplo

```java
CPFService cpf = new CPFService();

cpf.isValid("123.456.789-09");  // depende do CPF ser matematicamente válido
cpf.isValid("111.111.111-11");  // false (sequência repetida)
cpf.isValid("123");              // false (comprimento != 11)
cpf.isValid(null);                // false
```
