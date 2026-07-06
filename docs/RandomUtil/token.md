# RandomUtil.token(int limit)

**Pacote:** `io.github.sidneyroberto9.rotom.random`
**Arquivo:** `RandomUtil.java`

## Descrição

Gera um token aleatório truncando um UUID aleatório (sem hífens) para o tamanho informado.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `limit` | `int` | tamanho desejado do token (entre 1 e 32, inclusive) |

## Retorno

`String` — token de `limit` caracteres hexadecimais.

## Comportamento de borda

- **Lança `IllegalArgumentException`** se `limit < 1` ou `limit > 32` — um UUID sem hífens tem
  exatamente 32 caracteres, então esse é o teto (diferente do `PasswordUtil.getToken` original do
  m4all, que não validava o limite e lançava `StringIndexOutOfBoundsException` para `limit > 36`
  no UUID **com** hífens).
- Cada chamada gera um UUID novo — sem estado compartilhado entre chamadas.

## Exemplo

```java
RandomUtil random = new RandomUtil();

random.token(10);  // ex. "a3f2b7c8d9" (10 primeiros chars de um UUID sem hífens)
random.token(33);   // lança IllegalArgumentException (> 32)
random.token(0);     // lança IllegalArgumentException (< 1)
```
