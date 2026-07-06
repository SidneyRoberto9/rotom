# CNPJService

Pacote: `io.github.sidneyroberto9.rotom.cnpj`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/cnpj/CNPJService.java`

Serviço de validação e formatação de CNPJ (Cadastro Nacional da Pessoa Jurídica). Todos os métodos
aceitam CNPJ com ou sem máscara. Todos os métodos são de **instância**.

## Construtor

```java
CNPJService cnpj = new CNPJService();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `CNPJService` fica disponível para injeção automática.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`format(String cnpj)`](format.md) | `String` | Formata para `XX.XXX.XXX/XXXX-XX` |
| [`strip(String cnpj)`](strip.md) | `String` | Remove máscara, deixa só dígitos |
| [`isFormatted(String cnpj)`](isFormatted.md) | `boolean` | Verifica se já está no padrão mascarado |
| [`isValid(String cnpj)`](isValid.md) | `boolean` | Valida os dois dígitos verificadores |
