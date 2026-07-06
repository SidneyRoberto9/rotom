# CollectionUtils.removeDuplicates(List&lt;T&gt; list)

**Pacote:** `io.github.sidneyroberto9.rotom.collections`
**Arquivo:** `CollectionUtils.java`

## Descrição

Remove elementos duplicados da lista informada, preservando a ordem da primeira ocorrência de cada
elemento.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `list` | `List<T>` | lista a deduplicar |

## Retorno

`List<T>` — nova lista sem duplicatas, na ordem original.

## Comportamento de borda

- **Lança `NullPointerException`** se `list` for `null`.
- Genérico (`<T>`) — funciona para qualquer tipo de elemento, não só `String` (diferente do
  `ArrayUtil.removeDuplicates(List<String>)` original do m4all, que era restrito a `String`).
- Implementado com `new ArrayList<>(new LinkedHashSet<>(list))` — **O(n)**, usando `equals()`/
  `hashCode()` dos elementos. O `ArrayUtil.removeDuplicates` original era O(n²) com um laço
  aninhado comparando `.equals()` manualmente (mesmo resultado, mais lento para listas grandes).
- Elementos `null` na lista são preservados como um único `null` (comportamento padrão de
  `LinkedHashSet`).

## Exemplo

```java
CollectionUtils collectionUtils = new CollectionUtils();

collectionUtils.removeDuplicates(List.of("a", "b", "a", "c", "b"));
// ["a", "b", "c"]

collectionUtils.removeDuplicates(List.of(1, 2, 2, 3));
// [1, 2, 3]
```
