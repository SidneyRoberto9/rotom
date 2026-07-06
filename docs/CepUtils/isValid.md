# CepUtils.isValid(String cep)

**Pacote:** `io.github.sidneyroberto9.rotom.cep.domain`
**Arquivo:** `CepUtils.java`

## Descrição

Verifica se o CEP tem exatamente 8 dígitos numéricos. **O input deve estar normalizado (sem
máscara) antes de chamar este método** — não faz `normalize` internamente.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cep` | `String` | CEP apenas com dígitos |

## Retorno

`boolean` — `true` se tiver exatamente 8 dígitos, `false` caso contrário.

## Comportamento de borda

- `cep == null` → `false`.
- **Não remove máscara** — um CEP mascarado como `"58038-000"` (9 caracteres, incluindo o hífen)
  retorna `false`, mesmo tendo 8 dígitos numéricos. Sempre chamar [`normalize`](normalize.md)
  antes, se o input puder vir mascarado.
- Usa `matches()` sobre o regex `\d{8}` — exige exatamente 8 dígitos, nem mais nem menos.

## Exemplo

```java
CepUtils cepUtils = new CepUtils();

cepUtils.isValid("58038000");    // true
cepUtils.isValid("58038-000");   // false (não normalizado — tem hífen)
cepUtils.isValid(cepUtils.normalize("58038-000"));  // true (normalizado antes)
cepUtils.isValid(null);           // false
```
