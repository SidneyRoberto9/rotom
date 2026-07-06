# StringUtils.nameInitials(String fullName)

**Pacote:** `io.github.sidneyroberto9.rotom.strings`
**Arquivo:** `StringUtils.java`

*Introduzido na v1.1.0*.

## Descrição

Extrai iniciais de um nome completo, para exibição estilo avatar. Um nome de uma só palavra
retorna os 2 primeiros caracteres maiúsculos; um nome com múltiplas palavras retorna a inicial
maiúscula da primeira e da última palavra.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `fullName` | `String` | nome completo |

## Retorno

`String` — iniciais em maiúsculas, ou `"?"` se `fullName` for `null` ou blank.

## Comportamento de borda

- `fullName` null/blank → `"?"` (não lança exceção, não retorna `""`).
- Nome de uma palavra com **1 único caractere** → retorna esse único caractere maiúsculo (não
  duplicado) — `Math.min(2, word.length())` evita `StringIndexOutOfBoundsException`.
- Nome com 2+ palavras → só a **primeira e a última** contam, palavras do meio são ignoradas
  (ex. `"João da Silva Souza"` → `"JS"`, não `"JD"` nem iniciais de todas as palavras).

## Exemplo

```java
StringUtils str = new StringUtils();

str.nameInitials("Maria");                  // "MA"
str.nameInitials("João Silva");             // "JS"
str.nameInitials("João da Silva Souza");    // "JS" (só 1ª e última palavra)
str.nameInitials(null);                       // "?"
```
