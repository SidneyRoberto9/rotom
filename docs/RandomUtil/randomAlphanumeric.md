# RandomUtil.randomAlphanumeric(int size)

**Pacote:** `io.github.sidneyroberto9.rotom.random`
**Arquivo:** `RandomUtil.java`

## Descrição

Gera uma string aleatória composta por letras maiúsculas (`A-Z`) e dígitos (`0-9`), do tamanho
informado.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `size` | `int` | tamanho desejado (deve ser ≥ 0) |

## Retorno

`String` — string alfanumérica aleatória de comprimento `size`.

## Comportamento de borda

- **Lança `IllegalArgumentException`** se `size < 0`.
- `size == 0` → retorna `""`.
- Alfabeto usado: `ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789` (36 caracteres).

## Exemplo

```java
RandomUtil random = new RandomUtil();

random.randomAlphanumeric(8);  // ex. "K3P9XZ2Q"
random.randomAlphanumeric(0);  // ""
random.randomAlphanumeric(-1); // lança IllegalArgumentException
```
