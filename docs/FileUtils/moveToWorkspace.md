# FileUtils.moveToWorkspace(...)

**Pacote:** `io.github.sidneyroberto9.rotom.io`
**Arquivo:** `FileUtils.java`

## Assinaturas

```java
File moveToWorkspace(File file) throws IOException
File moveToWorkspace(File file, String name) throws IOException
```

## Descrição

Move o arquivo informado para o diretório de trabalho atual (`System.getProperty("user.dir")`),
opcionalmente renomeando-o. Quando um novo nome é informado, a **extensão original é preservada**.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `file` | `File` | arquivo a mover |
| `name` | `String` | novo nome base (sem extensão), ou `null`/vazio para manter o nome original |

## Retorno

`File` — referência ao arquivo já na nova localização.

## Comportamento de borda

- **Não força extensão `.pdf`** — diferente do `FileUtil.moveFileToWorkspace` original do m4all,
  que sempre anexava `.pdf` ao destino independente do tipo real do arquivo. Aqui, a extensão do
  arquivo de origem é preservada (ou nenhuma, se o arquivo original não tiver extensão).
  `overload(File)` delega para `overload(File, null)`, mantendo o nome original.
- **Sobrescreve** um arquivo existente no destino (`StandardCopyOption.REPLACE_EXISTING`) — sem
  aviso.
- Implementado com `java.nio.file.Files.move` (JDK puro) — **sem dependência do Google Guava**,
  que o original usava (`com.google.common.io.Files.move`).
- **Lança `IOException`** se o arquivo de origem não existir ou o destino não puder ser escrito —
  propagada diretamente (o original engolia a exceção e apenas imprimia no `System.err`).

## Exemplo

```java
FileUtils fileUtils = new FileUtils();

fileUtils.moveToWorkspace(new File("/tmp/upload.pdf"));
// move para {user.dir}/upload.pdf

fileUtils.moveToWorkspace(new File("/tmp/upload.pdf"), "contrato-123");
// move para {user.dir}/contrato-123.pdf (extensão preservada)
```
