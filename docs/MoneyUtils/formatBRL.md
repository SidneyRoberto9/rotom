# MoneyUtils.formatBRL(...)

**Pacote:** `io.github.sidneyroberto9.rotom.money`
**Arquivo:** `MoneyUtils.java`

## Assinaturas

```java
String formatBRL(double value)
String formatBRL(BigDecimal value)
```

## Descrição

Formata o valor informado como moeda brasileira (Real), usando
`NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))`.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `value` | `double` \| `BigDecimal` | valor a formatar |

## Retorno

`String` — valor formatado, ex. `R$ 1.234,56`.

## Comportamento de borda

- Overload `BigDecimal`: **lança `NullPointerException`** se `value` for `null`.
- Overload `double`: valores negativos são formatados com `-` antes de `R$` (ex. `-R$ 10,00`),
  seguindo o comportamento padrão do `NumberFormat` da JVM.
- Arredondamento segue o padrão de `NumberFormat` (2 casas decimais, `HALF_EVEN` por padrão) — para
  controle fino de arredondamento monetário, prefira formatar um `BigDecimal` já arredondado
  explicitamente antes de chamar este método.
- Depende do locale `pt-BR` estar disponível na JVM (built-in em qualquer JDK padrão).

## Exemplo

```java
MoneyUtils money = new MoneyUtils();

money.formatBRL(1234.56);                      // "R$ 1.234,56"
money.formatBRL(new BigDecimal("1234.56"));    // "R$ 1.234,56"
money.formatBRL(-10.0);                          // "-R$ 10,00"
```
