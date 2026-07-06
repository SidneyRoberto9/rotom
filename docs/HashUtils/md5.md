# HashUtils.md5(byte[] data)

**Pacote:** `io.github.sidneyroberto9.rotom.hash`
**Arquivo:** `HashUtils.java`

## Descrição

Atalho para [`hash(data, "MD5")`](hash.md).

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `data` | `byte[]` | bytes a hashear |

## Retorno

`String` — hash MD5 em hexadecimal minúsculo (32 caracteres).

## Comportamento de borda

MD5 **não é adequado para segurança** (senhas, assinaturas) — use apenas para checksums de
integridade não-críticos. Para segurança, prefira [`sha256`](sha256.md)/[`sha512`](sha512.md).

## Exemplo

```java
HashUtils hash = new HashUtils();

hash.md5("hello world".getBytes());  // "5eb63bbbe01eeed093cb22bb8f5acdc3"
```
