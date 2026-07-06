# Gsm7Converter.convertToGsm7(String input)

**Pacote:** `io.github.sidneyroberto9.rotom.sms`
**Arquivo:** `Gsm7Converter.java`

## Descrição

Converte o texto informado para o alfabeto padrão GSM 7-bit usado por corpos de SMS. Caracteres já
presentes no charset GSM-7 passam sem alteração; acentos mapeados são convertidos para o
equivalente ASCII mais próximo (ex. `á`→`a`, `Ç`→`C`); qualquer outro caractere é **descartado
silenciosamente**.

## Parâmetros

| Nome | Tipo | Significado |
|---|---|---|
| `input` | `String` | texto a converter (tipicamente o corpo de um SMS) |

## Retorno

`String` — texto restrito ao charset GSM-7.

## Comportamento de borda

- **Lança `NullPointerException`** se `input` for `null`.
- **Caracteres sem mapeamento são removidos, não substituídos** — ex. emojis, a maioria dos
  caracteres CJK, e símbolos fora do mapa de acentos desaparecem do resultado sem aviso. Isso é
  fiel ao comportamento do `Utf8ToGsm7ConverterService` original.
- O mapa de acentos (`á, à, â, ã, ä, é, è, ê, ë, í, ì, î, ï, ó, ò, ô, õ, ö, ú, ù, û, ü, ç, ñ, ý, ÿ` e
  suas maiúsculas) é fixo, sem opção de customização.
- `GSM_7_CHARSET` contém `\n` e `\r` literais e símbolos Unicode (`£¥Δ ΦΓΛΩΠΨΣΘΞ` etc.) que
  **passam direto** — o método não normaliza quebras de linha.

## Exemplo

```java
Gsm7Converter gsm7 = new Gsm7Converter();

gsm7.convertToGsm7("Olá, você recebeu um código!");
// "Ola, voce recebeu um codigo!" (acentos convertidos, resto já é GSM-7)

gsm7.convertToGsm7("emoji 😀 aqui");
// "emoji  aqui" (emoji removido silenciosamente)
```
