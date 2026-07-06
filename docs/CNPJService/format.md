# CNPJService.format(String cnpj)

**Pacote:** `io.github.sidneyroberto9.rotom.cnpj`
**Arquivo:** `CNPJService.java`

## Descrição

Formata o CNPJ para o padrão `XX.XXX.XXX/XXXX-XX`. Chama [`strip`](strip.md) primeiro, depois
aplica a máscara via regex.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cnpj` | `String` | CNPJ com ou sem máscara |

## Retorno

`String` — CNPJ formatado como `XX.XXX.XXX/XXXX-XX`.

## Comportamento de borda

- **`cnpj == null` → lança `NullPointerException`** (mesmo padrão de `CPFService.format` — `strip`
  retorna `null` e `.replaceFirst` é chamado sem checagem).
- Não valida 14 dígitos antes de formatar: se o regex `(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})` não
  casar, retorna a string sem máscara, sem erro.
- Não valida dígitos verificadores — formata mesmo um CNPJ matematicamente inválido.

## Exemplo

```java
CNPJService cnpj = new CNPJService();

cnpj.format("12345678000195");        // "12.345.678/0001-95"
cnpj.format("12.345.678/0001-95");    // idempotente
cnpj.format(null);                    // lança NullPointerException
```
