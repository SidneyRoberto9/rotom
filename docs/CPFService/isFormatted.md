# CPFService.isFormatted(String cpf)

**Pacote:** `io.github.sidneyroberto9.rotom.cpf`
**Arquivo:** `CPFService.java`

## Descrição

Verifica se o CPF já está no padrão mascarado `XXX.XXX.XXX-XX`, usando o regex
`\d{3}\.\d{3}\.\d{3}-\d{2}` com `matches()` (casamento completo da string).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cpf` | `String` | CPF a verificar |

## Retorno

`boolean` — `true` se já estiver formatado, `false` caso contrário.

## Comportamento de borda

- `cpf == null` → retorna `false` (não lança exceção).
- Não valida dígitos verificadores — apenas o formato visual. `"111.111.111-11"` retorna `true`
  mesmo sendo um CPF matematicamente inválido.
- Usa `matches()`, não `find()` — a string inteira precisa casar; caracteres extras antes/depois
  fazem retornar `false`.

## Exemplo

```java
CPFService cpf = new CPFService();

cpf.isFormatted("123.456.789-09");  // true
cpf.isFormatted("12345678909");     // false
cpf.isFormatted(null);              // false
cpf.isFormatted("111.111.111-11");  // true (formato ok, mas inválido matematicamente)
```
