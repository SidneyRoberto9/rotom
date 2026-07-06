# CollectionUtils

Pacote: `io.github.sidneyroberto9.rotom.collections`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/collections/CollectionUtils.java`

Utilitário para operações comuns de coleção. Introduzido na v1.1.0, usa
`LinkedHashSet` (O(n)) para deduplicação em vez de laço aninhado O(n²).

## Construtor

```java
CollectionUtils collectionUtils = new CollectionUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `CollectionUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`<T> removeDuplicates(List<T> list)`](removeDuplicates.md) | `List<T>` | remove duplicatas preservando ordem |
