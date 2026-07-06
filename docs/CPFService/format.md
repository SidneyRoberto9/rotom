# CPFService.format(String cpf)

**Pacote:** `io.github.sidneyroberto9.rotom.cpf`
**Arquivo:** `CPFService.java`

## Descrição

Formata o CPF para o padrão `XXX.XXX.XXX-XX`. Internamente chama [`strip`](strip.md) primeiro
(remove qualquer máscara existente) e depois aplica a máscara via regex.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cpf` | `String` | CPF com ou sem máscara |

## Retorno

`String` — CPF formatado como `XXX.XXX.XXX-XX`.

## Comportamento de borda

- **`cpf == null` → lança `NullPointerException`.** `strip(null)` retorna `null`, e o código chama
  `.replaceFirst(...)` diretamente nesse `null` sem checagem. Não há tratamento explícito — validar
  o input antes de chamar este método.
- Não valida se o CPF tem 11 dígitos antes de formatar. Se o input tiver menos ou mais dígitos que
  o esperado pelo regex `(\d{3})(\d{3})(\d{3})(\d{2})`, o `replaceFirst` simplesmente não casa e
  **retorna a string original sem máscara nenhuma** (sem erro).
- Não valida dígitos verificadores — formata mesmo um CPF matematicamente inválido. Use
  [`isValid`](isValid.md) separadamente se precisar garantir validade.

## Exemplo

```java
CPFService cpf = new CPFService();

cpf.format("12345678909");        // "123.456.789-09"
cpf.format("123.456.789-09");     // "123.456.789-09" (idempotente)
cpf.format(null);                 // lança NullPointerException
cpf.format("123");                // "123" (não casa o regex, retorna sem máscara)
```
