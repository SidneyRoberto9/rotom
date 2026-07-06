# FileUtils.toBase64(...)

**Pacote:** `io.github.sidneyroberto9.rotom.io`
**Arquivo:** `FileUtils.java`

## Assinaturas

```java
String toBase64(File file) throws IOException
String toBase64(String path) throws IOException
```

## Descrição

Lê o conteúdo do arquivo informado e codifica em Base64 (útil para embutir arquivos pequenos em
JSON, ex. anexos, imagens, PDFs).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `file` | `File` | arquivo a codificar |
| `path` | `String` | caminho para o arquivo a codificar |

## Retorno

`String` — conteúdo do arquivo codificado em Base64.

## Comportamento de borda

- **Lança `IOException`** se o arquivo não existir ou não puder ser lido — propagada diretamente,
  **sem** capturar/logar/engolir como o `FileUtil.encoder` original do m4all fazia (que imprimia
  no `System.out` e retornava `""` silenciosamente em caso de erro).
- Carrega **o arquivo inteiro em memória** (`Files.readAllBytes`) antes de codificar — não é
  adequado para arquivos muito grandes.
- `overload(String path)` delega para `overload(File)` via `new File(path)`.

## Exemplo

```java
FileUtils fileUtils = new FileUtils();

fileUtils.toBase64("/tmp/documento.pdf");
fileUtils.toBase64(new File("/tmp/documento.pdf"));

fileUtils.toBase64("/caminho/inexistente.pdf");  // lança IOException
```
