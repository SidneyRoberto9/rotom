# PhoneNumberService

Pacote: `io.github.sidneyroberto9.rotom.phoneNumber`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/phoneNumber/PhoneNumberService.java`

Serviço de formatação e validação de números de telefone brasileiros. Suporta celular (9 dígitos
locais) e fixo (8 dígitos locais), com ou sem DDD e com ou sem código de país (`+55`). Todos os
métodos são de **instância**.

## Construtor

```java
PhoneNumberService phone = new PhoneNumberService();
```

Mantém internamente o conjunto `VALID_DDDS` (67 DDDs válidos: 11–19, 21, 22, 24, 27, 28, 31–35,
37, 38, 41–49, 51, 53, 54, 55, 61–69, 71, 73–75, 77, 79, 81–89, 91–99).

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `PhoneNumberService` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`strip(String phone)`](strip.md) | `String` | Remove tudo que não é dígito |
| [`getDDD(String phone)`](getDDD.md) | `String` | Extrai os 2 dígitos do DDD |
| [`isMobile(String phone)`](isMobile.md) | `boolean` | Verifica se é celular |
| [`isLandline(String phone)`](isLandline.md) | `boolean` | Verifica se é fixo |
| [`isFormatted(String phone)`](isFormatted.md) | `boolean` | Verifica se já está mascarado |
| [`isValid(String phone)`](isValid.md) | `boolean` | Valida comprimento e DDD |
| [`format(String phone)`](format.md) | `String` | Formata detectando celular/fixo |
| [`formatWithCountryCode(String phone)`](formatWithCountryCode.md) | `String` | Formata com `+55` |
