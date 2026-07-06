# FileUtils.formatSize(long bytes)

**Pacote:** `io.github.sidneyroberto9.rotom.io`
**Arquivo:** `FileUtils.java`

## Descrição

Formata uma quantidade de bytes em uma representação legível (ex. `1.5 KB`, `3.2 MB`, `2.0 GB`),
usando múltiplos de 1024 (binário, não decimal).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `bytes` | `long` | tamanho em bytes (deve ser ≥ 0) |

## Retorno

`String` — tamanho formatado com 1 casa decimal e sufixo de unidade (`B`, `KB`, `MB`, `GB`, `TB`,
`PB`, `EB`).

## Comportamento de borda

- **Lança `IllegalArgumentException`** se `bytes < 0` (guarda adicionada; o `UtilFormats.formatSize`
  original do m4all não validava negativos e produzia saída sem sentido).
- `bytes < 1024` → retorna `"{bytes} B"` sem casas decimais.
- Usa múltiplos de **1024** (KiB/MiB reais), não 1000 — `1024` bytes vira `"1.0 KB"`, não
  `"1.02 KB"`.

## Exemplo

```java
FileUtils fileUtils = new FileUtils();

fileUtils.formatSize(500);        // "500 B"
fileUtils.formatSize(1536);       // "1.5 KB"
fileUtils.formatSize(3_500_000);  // "3.3 MB"
fileUtils.formatSize(-1);          // lança IllegalArgumentException
```
