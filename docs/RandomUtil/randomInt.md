# RandomUtil.randomInt(int min, int max)

**Pacote:** `io.github.sidneyroberto9.rotom.random`
**Arquivo:** `RandomUtil.java`

## Descrição

Gera um inteiro aleatório entre `min` e `max`, ambos inclusive.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `min` | `int` | limite inferior, inclusive |
| `max` | `int` | limite superior, inclusive |

## Retorno

`int` — valor aleatório no intervalo `[min, max]`.

## Comportamento de borda

- **Lança `IllegalArgumentException`** se `min > max`.
- `min == max` → sempre retorna esse valor único.
- Usa `SecureRandom.nextInt((max - min) + 1) + min`.

## Exemplo

```java
RandomUtil random = new RandomUtil();

random.randomInt(1, 10);   // inteiro entre 1 e 10, ambos inclusive
random.randomInt(5, 1);     // lança IllegalArgumentException
```
