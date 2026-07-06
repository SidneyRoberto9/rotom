# FileUtils

Pacote: `io.github.sidneyroberto9.rotom.io`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/io/FileUtils.java`

Utilitário para operações comuns de arquivo: codificação Base64, formatação de tamanho legível e
movimentação de arquivos para o diretório de trabalho. Introduzido na v1.1.0. Usa
`java.nio.file` para mover arquivos (sem depender do Google Guava) e não força extensão `.pdf`
no destino.

## Construtor

```java
FileUtils fileUtils = new FileUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `FileUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`toBase64(File file)` / `toBase64(String path)`](toBase64.md) | `String` | lê o arquivo e codifica em Base64 |
| [`formatSize(long bytes)`](formatSize.md) | `String` | formata bytes de forma legível (`1.5 KB`, `3.2 MB`) |
| [`moveToWorkspace(File file)` / `moveToWorkspace(File file, String name)`](moveToWorkspace.md) | `File` | move o arquivo para `user.dir` |
