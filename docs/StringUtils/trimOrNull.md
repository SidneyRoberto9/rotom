# StringUtils.trimOrNull(String value)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Remove espaços das duas pontas da string. Retorna `null` se a string for null, vazia, ou só
espaços (em vez de retornar `""`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a processar |

## Retorno

`String` — string aparada, ou `null` se blank.

## Comportamento de borda

- Diferente de um `trim()` comum: uma string `"   "` vira `null`, não `""`.
- Útil para normalizar campos opcionais vindos de formulários antes de persistir.

## Exemplo

```java
StringUtils str = new StringUtils();

str.trimOrNull("  oi  ");  // "oi"
str.trimOrNull("   ");      // null
str.trimOrNull(null);        // null
```
