# MaskUtils.maskCpf(String cpf)

**Pacote:** `io.github.sidneyroberto9.rotom.mask`
**Arquivo:** `MaskUtils.java`

## Descrição

Mascara um CPF, revelando apenas os 3 primeiros e os 2 últimos dígitos.
Exemplo: `"12345678909"` → `"123.***.***-09"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cpf` | `String` | CPF com ou sem máscara |

## Retorno

`String` — CPF mascarado, `null` se o input for `null`, ou `"***********"` (11 asteriscos) se o
CPF, após remover a máscara, não tiver exatamente 11 dígitos.

## Comportamento de borda

- `cpf == null` → `null`.
- Comprimento diferente de 11 dígitos após limpar a máscara → retorna a string fixa de 11
  asteriscos (não lança exceção, não retorna o input original).
- Não valida dígitos verificadores — mascara qualquer sequência de 11 dígitos, mesmo um CPF
  matematicamente inválido.

## Exemplo

```java
MaskUtils maskUtils = new MaskUtils();

maskUtils.maskCpf("12345678909");        // "123.***.***-09"
maskUtils.maskCpf("123.456.789-09");     // "123.***.***-09" (aceita mascarado)
maskUtils.maskCpf("123");                 // "***********" (comprimento inválido)
maskUtils.maskCpf(null);                   // null
```
