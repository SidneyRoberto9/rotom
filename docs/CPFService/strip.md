# CPFService.strip(String cpf)

**Pacote:** `io.github.sidneyroberto9.rotom.cpf`
**Arquivo:** `CPFService.java`

## Descrição

Remove a máscara do CPF, retornando apenas os dígitos numéricos (via `replaceAll("\\D", "")`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cpf` | `String` | CPF com ou sem máscara |

## Retorno

`String` — apenas dígitos, ou `null` se o input for `null`.

## Comportamento de borda

- `cpf == null` → retorna `null` (não lança exceção).
- Não valida quantidade de dígitos — apenas remove tudo que não é `[0-9]`. Uma string sem nenhum
  dígito retorna `""`.

## Exemplo

```java
CPFService cpf = new CPFService();

cpf.strip("123.456.789-09");   // "12345678909"
cpf.strip("12345678909");      // "12345678909" (idempotente)
cpf.strip(null);                // null
cpf.strip("abc");               // ""
```
