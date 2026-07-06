# StringUtils.requireNonBlank(String value, String message)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

## Descrição

Garante que a string não é blank, lançando exceção com a mensagem fornecida caso contrário.
Útil como guarda de validação em construtores/setters.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `String` | string a validar |
| `message` | `String` | mensagem da exceção se `value` for blank |

## Retorno

`String` — o valor **aparado** (`value.trim()`), se válido.

## Comportamento de borda

- **Lança `IllegalArgumentException(message)`** se `value` for null, vazia, ou só espaços
  (usa [`isBlank`](isBlank.md) internamente).
- Retorna a string com `trim()` aplicado, não a string original intacta.

## Exemplo

```java
StringUtils str = new StringUtils();

str.requireNonBlank("  nome  ", "nome obrigatório");  // "nome"
str.requireNonBlank("   ", "nome obrigatório");        // lança IllegalArgumentException
str.requireNonBlank(null, "nome obrigatório");          // lança IllegalArgumentException
```
