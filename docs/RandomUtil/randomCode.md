# RandomUtil.randomCode(int size)

**Pacote:** `io.github.sidneyroberto9.rotom.random`
**Arquivo:** `RandomUtil.java`

## Descrição

Gera um código alfanumérico aleatório do tamanho informado. É um alias semântico de
[`randomAlphanumeric`](randomAlphanumeric.md), pensado para casos de uso como códigos de
verificação/convite (equivalente ao `EncodeService.generateRandomCode` do m4all, que gerava
sempre 6 caracteres — aqui o tamanho é parametrizável).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `size` | `int` | tamanho desejado (deve ser ≥ 0) |

## Retorno

`String` — código alfanumérico aleatório.

## Comportamento de borda

Idêntico a [`randomAlphanumeric`](randomAlphanumeric.md) — **lança `IllegalArgumentException`** se
`size < 0`.

## Exemplo

```java
RandomUtil random = new RandomUtil();

random.randomCode(6);  // ex. "A3F9K2" (código de convite/verificação)
```
