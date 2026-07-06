# PasswordValidator

Pacote: `io.github.sidneyroberto9.rotom.validation`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/validation/PasswordValidator.java`

Utilitário para validar a força de senhas. Introduzido na v1.1.0 a partir de `PasswordValidator`
(regex `PASSWORD_PATTERN`), duplicado em 12 arquivos do m4all (ver [`docs/AUDIT.md`](../AUDIT.md)).

## Construtor

```java
PasswordValidator passwordValidator = new PasswordValidator();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `PasswordValidator` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`isStrong(String password)`](isStrong.md) | `boolean` | verifica se a senha atende aos critérios de força |
