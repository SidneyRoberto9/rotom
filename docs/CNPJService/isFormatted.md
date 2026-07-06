# CNPJService.isFormatted(String cnpj)

**Pacote:** `io.github.sidneyroberto9.rotom.cnpj`
**Arquivo:** `CNPJService.java`

## Descrição

Verifica se o CNPJ já está no padrão mascarado `XX.XXX.XXX/XXXX-XX`, via regex com `matches()`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cnpj` | `String` | CNPJ a verificar |

## Retorno

`boolean` — `true` se já formatado, `false` caso contrário.

## Comportamento de borda

- `cnpj == null` → retorna `false`.
- Não valida dígitos verificadores, apenas o formato visual.

## Exemplo

```java
CNPJService cnpj = new CNPJService();

cnpj.isFormatted("12.345.678/0001-95");  // true
cnpj.isFormatted("12345678000195");      // false
cnpj.isFormatted(null);                  // false
```
