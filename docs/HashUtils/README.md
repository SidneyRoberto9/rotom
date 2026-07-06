# HashUtils

Pacote: `io.github.sidneyroberto9.rotom.hash`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/hash/HashUtils.java`

Utilitário para cálculo de hashes criptográficos (checksums) de bytes, strings, streams e
arquivos. Introduzido na v1.1.0. Em caso de arquivo não encontrado propaga `IOException` (sem
`System.exit(0)`) e usa try-with-resources para fechar o `FileInputStream`.

## Construtor

```java
HashUtils hash = new HashUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `HashUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`hash(byte[] data, String algorithm)`](hash.md) | `String` | hash hexadecimal dos bytes |
| [`hash(String text, String algorithm)`](hash.md) | `String` | hash hexadecimal do texto (UTF-8) |
| [`hash(InputStream in, String algorithm)`](hash.md) | `String` | hash hexadecimal do stream (não fecha) |
| [`hash(File file, String algorithm)`](hash.md) | `String` | hash hexadecimal do arquivo (fecha o stream) |
| [`md5(byte[] data)`](md5.md) | `String` | atalho para `hash(data, "MD5")` |
| [`sha256(byte[] data)`](sha256.md) | `String` | atalho para `hash(data, "SHA-256")` |
| [`sha512(byte[] data)`](sha512.md) | `String` | atalho para `hash(data, "SHA-512")` |
