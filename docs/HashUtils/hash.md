# HashUtils.hash(...)

**Pacote:** `io.github.sidneyroberto9.rotom.hash`
**Arquivo:** `HashUtils.java`

## Assinaturas

```java
String hash(byte[] data, String algorithm)
String hash(String text, String algorithm)
String hash(InputStream inputStream, String algorithm) throws IOException
String hash(File file, String algorithm) throws IOException
```

## Descrição

Calcula o hash do conteúdo informado usando o algoritmo dado (qualquer nome aceito por
`java.security.MessageDigest`, ex. `MD5`, `SHA-1`, `SHA-256`, `SHA-512`).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `data` | `byte[]` | bytes a hashear |
| `text` | `String` | texto a hashear (convertido para bytes UTF-8) |
| `inputStream` | `InputStream` | stream a hashear |
| `file` | `File` | arquivo a hashear |
| `algorithm` | `String` | nome do algoritmo (`MessageDigest`) |

## Retorno

`String` — hash em hexadecimal minúsculo.

## Comportamento de borda

- **Lança `IllegalArgumentException`** se `algorithm` não for suportado pela JVM (`NoSuchAlgorithmException`
  é capturada e relançada como `IllegalArgumentException` com causa encadeada).
- **Overload `InputStream`**: lê o stream até o fim, mas **não fecha** — fechar é responsabilidade
  de quem chamou (diferente do overload `File`, que abre e fecha seu próprio `FileInputStream`
  via try-with-resources).
- **Overload `File`**: propaga `IOException` se o arquivo não existir ou não puder ser lido — o
  `HashCheckSumUtil` original chamava `System.exit(0)` nesse caso, o que **não** acontece aqui.
- `data`/`text`/`file`/`inputStream` nulos → `NullPointerException` (sem tratamento especial).

## Exemplo

```java
HashUtils hash = new HashUtils();

hash.hash("hello world".getBytes(), "SHA-256");
hash.hash("hello world", "SHA-256");           // mesmo resultado que acima (UTF-8)
hash.hash(new File("/tmp/arquivo.txt"), "SHA-512");
hash.hash("texto", "ALGORITMO-INVALIDO");       // lança IllegalArgumentException
```
