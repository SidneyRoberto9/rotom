# MaskUtils

Pacote: `io.github.sidneyroberto9.rotom.mask`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/mask/MaskUtils.java`

Utilitário para mascarar números de documento sensíveis, para exibição amigável à LGPD.
Introduzido na v1.1.0 a partir de `LgpdMaskUtil.maskCpf/maskCnpj` do m4all
(ver [`docs/AUDIT.md`](../AUDIT.md)). Diferente de [`CPFService`](../CPFService/README.md) e
[`CNPJService`](../CNPJService/README.md), que apenas aplicam a máscara padrão de pontuação, os
métodos aqui **ocultam a maior parte dos dígitos**.

## Construtor

```java
MaskUtils maskUtils = new MaskUtils();
```

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `MaskUtils` fica disponível para injeção.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`maskCpf(String cpf)`](maskCpf.md) | `String` | mostra só os 3 primeiros e 2 últimos dígitos |
| [`maskCnpj(String cnpj)`](maskCnpj.md) | `String` | mostra só os 2 primeiros dígitos e o bloco da filial |
