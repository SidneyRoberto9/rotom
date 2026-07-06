# CPFService

Pacote: `io.github.sidneyroberto9.rotom.cpf`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/cpf/CPFService.java`

Serviço de validação e formatação de CPF (Cadastro de Pessoas Físicas). Todos os métodos aceitam
CPF com ou sem máscara. Todos os métodos são de **instância** (não estáticos).

## Construtor

```java
CPFService cpf = new CPFService();
```

Sem parâmetros, sem estado além do `Pattern` de formatação compilado internamente.

## Bean Spring

Se o auto-configure da lib estiver ativo (`RotomAutoConfiguration`), um bean `CPFService` já fica
disponível para injeção — não é necessário declarar `new CPFService()` manualmente em apps Spring Boot.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`format(String cpf)`](format.md) | `String` | Formata para `XXX.XXX.XXX-XX` |
| [`strip(String cpf)`](strip.md) | `String` | Remove máscara, deixa só dígitos |
| [`isFormatted(String cpf)`](isFormatted.md) | `boolean` | Verifica se já está no padrão mascarado |
| [`isValid(String cpf)`](isValid.md) | `boolean` | Valida dígitos verificadores |
