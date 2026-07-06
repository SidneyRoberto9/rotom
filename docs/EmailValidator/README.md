# EmailValidator

Pacote: `io.github.sidneyroberto9.rotom.validation`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/validation/EmailValidator.java`

Utilitário para validar a estrutura de endereços de e-mail. Introduzido na v1.1.0 a partir de
`ValidationsUtil.isValidEmailAddress` do m4all (ver [`docs/AUDIT.md`](../AUDIT.md)). O original
usava `javax.mail.internet.InternetAddress`; esta versão usa **regex**, evitando adicionar
`javax.mail` como dependência da lib (decisão de reimplementação enxuta).

## Construtor

```java
EmailValidator emailValidator = new EmailValidator();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `EmailValidator` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`isValid(String email)`](isValid.md) | `boolean` | verifica se o e-mail é estruturalmente válido |
