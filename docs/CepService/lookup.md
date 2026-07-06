# CepService.lookup(String rawCep)

**Pacote:** `io.github.sidneyroberto9.rotom.cep.domain`
**Arquivo:** `CepService.java`

## Descrição

Busca o endereço do CEP informado, consultando os providers configurados em sequência. Se um
provider falhar (exceção) ou não retornar dado, o próximo é tentado automaticamente.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `rawCep` | `String` | CEP com ou sem máscara (ex. `58038-000` ou `58038000`) |

## Retorno

`Address` — objeto com os dados do endereço encontrado.

## Comportamento de borda

- **Lança `IllegalArgumentException("CEP inválido: " + rawCep)`** se o CEP, após normalizado, não
  tiver exatamente 8 dígitos (via [`CepUtils.normalize`](../CepUtils/normalize.md) +
  [`isValid`](../CepUtils/isValid.md)).
- Itera os providers **em ordem**; qualquer `Exception` lançada por um provider (`IOException` de
  rede, parsing, etc.) é **silenciosamente ignorada** (`catch (Exception ignored) {}`) e o próximo
  provider é tentado. Não há log nem propagação de qual provider falhou e por quê.
- Se **nenhum** provider retornar dado, retorna um `Address` com **todos os campos `null` exceto
  `cep`** (que recebe o CEP normalizado) — não lança exceção nesse caso, então sempre checar se os
  campos relevantes (ex. `getLogradouro()`) vieram preenchidos antes de usar.
- Chamadas de rede (HTTP via OkHttp) são **síncronas e bloqueantes** — em caso de fallback total
  (todos os providers falham), o tempo de resposta é a soma do timeout de cada provider tentado.

## Exemplo

```java
CepService cepService = new CepService();

Address addr = cepService.lookup("58038-000");
addr.getLogradouro();  // rua, se algum provider retornou dado
addr.getLocalidade();  // cidade
addr.getUf();          // estado (sigla)

cepService.lookup("123");  // lança IllegalArgumentException (CEP inválido)

// CEP válido mas inexistente / todos providers falharam:
Address vazio = cepService.lookup("00000000");
vazio.getCep();         // "00000000"
vazio.getLogradouro();  // null
```
