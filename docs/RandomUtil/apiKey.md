# RandomUtil.apiKey() / apiKey(String prefix)

**Pacote:** `io.github.sidneyroberto9.rotom.random`
**Arquivo:** `RandomUtil.java`

## Assinaturas

```java
String apiKey()
String apiKey(String prefix)
```

## Descrição

Gera uma API key composta por um prefixo seguido de 12 caracteres hexadecimais derivados de um
UUID aleatório. `apiKey()` usa o prefixo padrão `sk_live_`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `prefix` | `String` | prefixo da chave (ex. `sk_live_`, `sk_test_`) |

## Retorno

`String` — API key gerada, ex. `sk_live_a3f2b7c8d901`.

## Comportamento de borda

- `apiKey(String prefix)` não valida `prefix` — `null` faz a concatenação produzir a string
  literal `"null" + 12 chars`.
- Os 12 caracteres vêm de `UUID.randomUUID().toString().replace("-", "").substring(0, 12)` — não é
  garantidamente único entre chamadas (colisão teoricamente possível, embora extremamente rara).

## Exemplo

```java
RandomUtil random = new RandomUtil();

random.apiKey();               // "sk_live_a3f2b7c8d901"
random.apiKey("sk_test_");     // "sk_test_f91ac02b77e4"
```
