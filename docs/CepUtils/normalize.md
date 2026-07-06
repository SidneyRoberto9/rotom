# CepUtils.normalize(String raw)

**Pacote:** `io.github.sidneyroberto9.rotom.cep.domain`
**Arquivo:** `CepUtils.java`

## Descrição

Remove todos os caracteres não numéricos do CEP.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `raw` | `String` | CEP em qualquer formato (ex. `58038-000` ou `58038000`) |

## Retorno

`String` — apenas dígitos, ou `null` se o input for `null`.

## Comportamento de borda

- `raw == null` → retorna `null`.
- Não valida quantidade de dígitos — apenas limpa. Para validar, use [`isValid`](isValid.md)
  depois de normalizar.

## Exemplo

```java
CepUtils cepUtils = new CepUtils();

cepUtils.normalize("58038-000");  // "58038000"
cepUtils.normalize(null);           // null
```
