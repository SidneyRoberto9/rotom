# MaskUtils.maskCnpj(String cnpj)

**Pacote:** `io.github.sidneyroberto9.rotom.mask`
**Arquivo:** `MaskUtils.java`

## Descrição

Mascara um CNPJ, revelando apenas os 2 primeiros dígitos e o bloco da filial (dígitos 9 a 12).
Exemplo: `"12345678000195"` → `"12.***.***/0001-**"`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cnpj` | `String` | CNPJ com ou sem máscara |

## Retorno

`String` — CNPJ mascarado, `null` se o input for `null`, ou `"**************"` (14 asteriscos) se
o CNPJ, após remover a máscara, não tiver exatamente 14 dígitos.

## Comportamento de borda

- `cnpj == null` → `null`.
- Comprimento diferente de 14 dígitos após limpar a máscara → retorna a string fixa de 14
  asteriscos.
- Não valida dígitos verificadores.
- O "bloco da filial" revelado é sempre os dígitos de índice 8–11 (ex. `0001` para a matriz) —
  útil para identificar visualmente a filial sem expor o restante do número.

## Exemplo

```java
MaskUtils maskUtils = new MaskUtils();

maskUtils.maskCnpj("12345678000195");         // "12.***.***/0001-**"
maskUtils.maskCnpj("12.345.678/0001-95");     // "12.***.***/0001-**" (aceita mascarado)
maskUtils.maskCnpj(null);                       // null
```
