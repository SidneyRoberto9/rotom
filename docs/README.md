# Documentação — rotom (io.github.sidneyroberto9)

Documentação detalhada, por método, de todos os serviços públicos da biblioteca. Cada classe tem
sua própria pasta com um `README.md` de visão geral e um arquivo `.md` por método.

Para a visão geral navegável (página única, estilo site) veja [`index.html`](index.html).
Para a auditoria de utilitários candidatos encontrados no monorepo `m4all`, veja
[`AUDIT.md`](AUDIT.md).

## Documentos (CPF / CNPJ)

- [`CPFService`](CPFService/README.md) — `io.github.sidneyroberto9.rotom.cpf`
- [`CNPJService`](CNPJService/README.md) — `io.github.sidneyroberto9.rotom.cnpj`

## Comunicação

- [`PhoneNumberService`](PhoneNumberService/README.md) — `io.github.sidneyroberto9.rotom.phoneNumber`
- [`Gsm7Converter`](Gsm7Converter/README.md) — `io.github.sidneyroberto9.rotom.sms` *(v1.1.0)*

## Texto

- [`StringUtils`](StringUtils/README.md) — `io.github.sidneyroberto9.rotom.strings`

## Data

- [`DateService`](DateService/README.md) — `io.github.sidneyroberto9.rotom.date` (dias úteis / feriados)
- [`DateUtils`](DateUtils/README.md) — `io.github.sidneyroberto9.rotom.date` (conversões / formatação)
- [`DurationUtils`](DurationUtils/README.md) — `io.github.sidneyroberto9.rotom.date` (duração legível em PT) *(v1.1.0)*
- [`BrasilApiHolidayService`](BrasilApiHolidayService/README.md) — `io.github.sidneyroberto9.rotom.date.holiday` (feriados via BrasilAPI, online) *(v1.1.0)*

## Localização (CEP)

- [`CepService`](CepService/README.md) — `io.github.sidneyroberto9.rotom.cep.domain` (busca com fallback multi-provider)
- [`CepUtils`](CepUtils/README.md) — `io.github.sidneyroberto9.rotom.cep.domain` (normalização/validação/formatação pura)

## Aleatoriedade & Codificação *(v1.1.0)*

- [`RandomUtil`](RandomUtil/README.md) — `io.github.sidneyroberto9.rotom.random` (números, strings, tokens, API keys aleatórias)
- [`EncodingUtils`](EncodingUtils/README.md) — `io.github.sidneyroberto9.rotom.encoding` (Base64 de texto e inteiros)
- [`HashUtils`](HashUtils/README.md) — `io.github.sidneyroberto9.rotom.hash` (MD5/SHA-256/SHA-512 de bytes, strings, streams, arquivos)

## Validação *(v1.1.0)*

- [`EmailValidator`](EmailValidator/README.md) — `io.github.sidneyroberto9.rotom.validation`
- [`PasswordValidator`](PasswordValidator/README.md) — `io.github.sidneyroberto9.rotom.validation`
- [`NumberUtils`](NumberUtils/README.md) — `io.github.sidneyroberto9.rotom.validation`

## Formatação, Máscara & Arquivos *(v1.1.0)*

- [`MoneyUtils`](MoneyUtils/README.md) — `io.github.sidneyroberto9.rotom.money` (moeda BRL)
- [`MaskUtils`](MaskUtils/README.md) — `io.github.sidneyroberto9.rotom.mask` (máscara LGPD de CPF/CNPJ)
- [`FileUtils`](FileUtils/README.md) — `io.github.sidneyroberto9.rotom.io` (Base64, tamanho legível, mover arquivo)

## Coleções *(v1.1.0)*

- [`CollectionUtils`](CollectionUtils/README.md) — `io.github.sidneyroberto9.rotom.collections`

## Auto-configuração Spring

Todas as 21 classes acima têm bean registrado por `RotomAutoConfiguration`
(`io.github.sidneyroberto9.rotom.autoconfigure`), condicionado a `@ConditionalOnMissingBean` — basta
ter a lib no classpath de um projeto Spring Boot para injetar qualquer um desses serviços sem
configuração adicional.

## Nota sobre fonte da verdade

Esta documentação foi gerada lendo diretamente o código-fonte em `src/main/java/...`, não o
`README.md` da raiz nem o `index.html` anterior — ambos continham imprecisões (ex. campos do
`Address` em inglês que não existem, afirmação incorreta de que todo método de `DateUtils` tem
overload para `Date`). Onde uma divergência foi encontrada, o comportamento real do código está
documentado explicitamente na seção "Comportamento de borda" de cada método.
