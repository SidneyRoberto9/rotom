# CNPJService.strip(String cnpj)

**Pacote:** `io.github.sidneyroberto9.rotom.cnpj`
**Arquivo:** `CNPJService.java`

## Descrição

Remove a máscara do CNPJ, retornando apenas os dígitos numéricos.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `cnpj` | `String` | CNPJ com ou sem máscara |

## Retorno

`String` — apenas dígitos, ou `null` se o input for `null`.

## Comportamento de borda

- `cnpj == null` → retorna `null`.
- Sem validação de comprimento; string sem dígitos retorna `""`.

## Exemplo

```java
CNPJService cnpj = new CNPJService();

cnpj.strip("12.345.678/0001-95");  // "12345678000195"
cnpj.strip(null);                   // null
```
