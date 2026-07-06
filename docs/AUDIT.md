# Auditoria — candidatos a novos utilitários (monorepo m4all)

Relatório de auditoria dos utilitários espalhados em `/home/sid/www/m4all` (~550 arquivos
util/helper/validator/formatter varridos), para decidir o que vale adicionar à lib `utils`
(`io.github.sidneyroberto9`).

> **Atualização v1.1.0:** a maior parte dos itens abaixo marcados **NOVO** foi implementada na lib
> — cada linha implementada está anotada com **✅ Implementado (v1.1.0)** e o método/classe
> correspondente. `ExcelProcessingUtilService` (Apache POI) e `StringListConverter` (JPA) foram
> **pulados** por decisão do usuário (evitar dependências pesadas); `EncryptId` foi **pulado** por
> ser não-determinístico/quebrado como util compartilhado. Itens **OVERLAP** seguem sem ação —
> permanecem candidatos de migração para o agente `UtilsLibIntegrator`.

Legenda: **NOVO** = não coberto pela lib hoje · **PARCIAL** = estende uma área já existente
(CPF/CNPJ/telefone/data/string/CEP) · **OVERLAP** = já coberto pela lib, cópia local deveria ser
aposentada.

---

## 1. Encoding / Random / IDs / Crypto (todos NOVO, alto valor)

| Método | O que faz | Projetos / caminhos | Duplicatas | Status |
|---|---|---|---|---|
| `RandomUtil.randInt/randString/randStringOnlyNumbers` | inteiro aleatório, string alfanumérica, string só-números | `elevaflow-api/.../util/RandomUtil.java` (+ inovadocs/vivo, inovadocs/soluarq, pg/vivo, portal-e2e, societario, uso-proprio/segmento7, venda-imoveis) | 8 projetos | ✅ Implementado (v1.1.0) — [`RandomUtil.randomInt/randomAlphanumeric/randomNumeric`](../RandomUtil/README.md) |
| `EncodeService.generateRandomCode/toBase64/fromBase64` | código aleatório 6 chars, Base64 encode/decode | `eleva_orders_api/.../util/services/EncodeService.java` (+ eleva-office, eleva-desk, elevadocs, eleva-messaging, eleva-contracts, eleva-helpdesk, eleva-supplies, modulo-rh, portal_inova) | ~10 projetos | ✅ Implementado (v1.1.0) — [`RandomUtil.randomCode`](../RandomUtil/randomCode.md) + [`EncodingUtils.toBase64/fromBase64`](../EncodingUtils/README.md) |
| `PasswordUtil.getToken(Integer limit)` | token = substring de UUID aleatório | `elevaflow-api/.../util/PasswordUtil.java` (+ inovadocs/vivo, pg/vivo, portal-e2e, societario, uso-proprio, venda-imoveis, inovadocs/soluarq) | 8 projetos | ✅ Implementado (v1.1.0) — [`RandomUtil.token`](../RandomUtil/token.md) |
| `ApiKeyGenerator.generateApiKey()` | API key prefixada (`sk_live_<12 hex>`) | `elevaflow-api/.../util/ApiKeyGenerator.java` | 1 projeto, genérico | ✅ Implementado (v1.1.0) — [`RandomUtil.apiKey`](../RandomUtil/apiKey.md) |
| `Base64Encoder.encode(int)/decode(String)` | ofuscação de ID int↔Base64 | `elevaflow-api/.../util/Base64Encoder.java` | 1 projeto | ✅ Implementado (v1.1.0) — [`EncodingUtils.encodeInt/decodeInt`](../EncodingUtils/README.md) |
| `EncryptId` | encriptação reversível de ID | `elevaflow-api`, `venda-imoveis` | 2 projetos | ❌ Pulado — não-determinístico/quebrado como util compartilhado (decisão do usuário); `EncodingUtils.encodeInt/decodeInt` já cobre ofuscação estável de ID |
| `HashCheckSumUtil.generateHash(...)` | checksum MD5/SHA-1/SHA-256/...-512 de arquivo/stream/byte[] | `portal-societario-api/.../util/HashCheckSumUtil.java` | 1 projeto, muito genérico | ✅ Implementado (v1.1.0) — [`HashUtils`](../HashUtils/README.md) (corrigido: sem `System.exit`, streams fechados) |

## 2. Texto / String (estende `StringUtils` da lib)

| Método | O que faz | Projetos / caminhos | Duplicatas | Status |
|---|---|---|---|---|
| `Utf8ToGsm7ConverterService.convertToGsm7` | remove acento / filtra fora do charset GSM-7 (corpo de SMS) | `eleva_orders_api/.../util/services/Utf8ToGsm7ConverterService.java` (+ eleva-office, eleva-desk, elevadocs, eleva-messaging, eleva-contracts, eleva-helpdesk, eleva-supplies, modulo-rh, portal_inova) | 8+ projetos, consumido por cada `TwilioService` local | ✅ Implementado (v1.1.0) — [`Gsm7Converter.convertToGsm7`](../Gsm7Converter/convertToGsm7.md) |
| `removeAcentos` / `Normalizer.normalize(NFD)` inline | remoção de acento | `StringFormatUtil.java` (societario, elevaflow, venda-imoveis, segmento7) + idioma repetido em outros arquivos | 44 arquivos usam o idioma `Normalizer.normalize(...NFD...)` | OVERLAP — já coberto por [`StringUtils.removeAccents`](../StringUtils/removeAccents.md); sem ação nesta versão (migração de chamadores, não criação) |
| Title-case PT-BR c/ conectivos — `getCapitalizedFullName`/`getCapitalizedNames`/`toTitleCase` | capitaliza preservando `da/de/do/dos/e` minúsculo | `StringUtilService` (eleva-office, eleva-desk, eleva-supplies, frotas, modulo-rh, elevadocs, portal_inova, inovadocs/vivo) + `toTitleCase` em frotas | ~24 arquivos | OVERLAP — já coberto por [`StringUtils.capitalizeWords`](../StringUtils/capitalizeWords.md); sem ação nesta versão |
| `removeSpecialCharacters` (`[^\d]` strip) | extrai só dígitos | espalhado em 31 arquivos | 31 arquivos — **PARCIAL**, sobrepõe `digitsOnly`/`strip` da lib mas como helper de string solto | OVERLAP — já coberto por [`StringUtils.digitsOnly`](../StringUtils/digitsOnly.md); sem ação nesta versão |
| `getEmailDomain(String)` | domínio em maiúsculas | `StringUtilService` cluster | 11 arquivos — **quase idêntico** a `StringUtils.emailDomain` já existente na lib | OVERLAP — já coberto por [`StringUtils.emailDomain`](../StringUtils/emailDomain.md); sem ação nesta versão |
| `StringFormatUtil.toCamelCaseWithSpaces` / `emptyStringOrOnlySpace` / `formatElevenDigitString` | camelCase com espaços, default se vazio, formatação de 11 dígitos | societario, elevaflow, venda-imoveis, uso-proprio/segmento7 | 4 projetos | ✅ Implementado (v1.1.0) — [`StringUtils.toCamelCaseWithSpaces/defaultIfBlank/formatElevenDigits`](../StringUtils/README.md) |
| `NameInitials.of(String)` | iniciais estilo avatar (2 letras) | `elevadesk-api/.../dashboard/util/NameInitials.java` | 1 projeto, genérico | ✅ Implementado (v1.1.0) — [`StringUtils.nameInitials`](../StringUtils/nameInitials.md) |

## 3. Validadores

| Método | O que faz | Projetos / caminhos | Duplicatas | Status |
|---|---|---|---|---|
| `ValidationsUtil.isValidEmailAddress` | valida e-mail via `javax.mail.internet.InternetAddress` | `elevaflow-api/.../util/ValidationsUtil.java` | 1 projeto direto + regex de e-mail inline no cluster `StringUtilService` (eleva-desk, eleva-office, eleva-supplies, frotas, inovadocs/vivo, modulo-rh, portal_inova) — **NOVO**, gap real na lib | ✅ Implementado (v1.1.0) — [`EmailValidator.isValid`](../EmailValidator/isValid.md) (reescrito com regex, sem dependência `javax.mail`) |
| `PasswordValidator.PASSWORD_PATTERN` | regex força de senha (8–16, maiúscula+minúscula+dígito+especial, sem espaço) | `elevaflow-api/.../util/PasswordValidator.java` (+ inovadocs/vivo, pg/vivo, portal-e2e, societario, uso-proprio, venda-imoveis, inovadocs/soluarq) | 12 arquivos — **NOVO** | ✅ Implementado (v1.1.0) — [`validation.PasswordValidator.isStrong`](../PasswordValidator/isStrong.md) |
| `NumberUtils.isInteger/isFloat/isNumeric/isDouble` | checagem de string numérica | `elevasign-helpdesk-api/.../util/NumberUtils.java` | 1 projeto — **NOVO** | ✅ Implementado (v1.1.0) — [`validation.NumberUtils`](../NumberUtils/README.md) |
| CPF/CNPJ inline (`Validacao`, `ValidationsUtil.isCPF/isCNPJ`, `CNPJValidator`, `CnpjValidator`/`CNPJService`, `CnpjValidatorService`) | validação de documento | segmento7, elevaflow, inovadocs/vivo, societario | **OVERLAP** — trocar por `CPFService`/`CNPJService` da lib | Sem ação — candidato de migração (`UtilsLibIntegrator`), não de criação |
| `PhoneNumberService.validate` (cluster local, nome colide com o da lib) | regex `^\+55(\d{2})9\d{8}$` + rejeita dígitos repetidos + allowlist de DDD | eleva-office, eleva-desk, eleva-supplies, modulo-rh, portal_inova | 5 projetos — **PARCIAL**: formatação sobrepõe `PhoneNumberService` da lib; o valor-agregado real é a allowlist de DDD (a lib já tem `VALID_DDDS`, então isso é quase um duplicado puro) | Sem ação — candidato de migração, não de criação |

## 4. Número / Moeda / Tamanho (todos NOVO)

| Método | O que faz | Projetos / caminhos | Duplicatas | Status |
|---|---|---|---|---|
| `MoedaUtil.formatarMoeda(double)` | formata em BRL (`NumberFormat.getCurrencyInstance(pt,BR)`) | `portal-societario-api/.../util/MoedaUtil.java` | idioma `getCurrencyInstance` repetido em 36 arquivos (`PrinterModelReportUtilService`, `InvoiceFieldParsingUtil`, `StringUtilService.formatPaperlessValue` de frotas, etc.) | ✅ Implementado (v1.1.0) — [`MoneyUtils.formatBRL`](../MoneyUtils/formatBRL.md) |
| `UtilFormats.formatSize(long)` | bytes → texto legível (B/KB/MB/...) | `portal-societario-api/.../util/UtilFormats.java` | idioma `numberOfLeadingZeros` em 3 arquivos | ✅ Implementado (v1.1.0) — [`FileUtils.formatSize`](../FileUtils/formatSize.md) |
| `LgpdMaskUtil.maskCpf/maskCnpj` | máscara LGPD (`123.***.***-90`) | `elevaflow-api/.../util/LgpdMaskUtil.java` | referenciado em 4 arquivos | ✅ Implementado (v1.1.0) — [`MaskUtils.maskCpf/maskCnpj`](../MaskUtils/README.md) |

## 5. Data / Dias úteis (estende `DateService`/`DateUtils`)

| Método | O que faz | Projetos / caminhos | Duplicatas | Status |
|---|---|---|---|---|
| Feriados nacionais + dias úteis (BrasilAPI) | busca `/api/feriados/v1/{year}` + soma dias úteis excluindo feriados | `HolidayService`+`TimeUtilService` (modulo-rh/rh-vivo-api), `BrasilApiHolidayService` (eleva-supplies), `NationalHolidayService` (eleva-desk), `FeriadoService`×2 (inovadocs/vivo, inovadocs/soluarq) | reimplementado **5 vezes com nomes diferentes** em ~6 projetos — a lib já resolve isso via `jollyday`/`DateService`, então é forte candidato a **consolidação + migração** | ✅ Implementado (v1.1.0) — [`BrasilApiHolidayService.getHolidays`](../BrasilApiHolidayService/getHolidays.md) (busca via okhttp+jackson; combine com [`DateService`](../DateService/README.md) para dias úteis) |
| `TimeDifference.calculateDaysDifference/...` | duração → texto PT-BR ("X dia(s) Y hora(s) e Z minuto(s)") | `elevasign-helpdesk-api/.../util/TimeDifference.java` | 1 projeto — **NOVO** | ✅ Implementado (v1.1.0) — [`DurationUtils`](../DurationUtils/README.md) (corrigido: limites `>=60`/`>=24`) |
| `SLAUtil` / `DeadLineUtil` / `DateDeadlineUtil` | cálculo de prazo/SLA sobre dias úteis | elevaflow, portal-e2e, uso-proprio, venda-imoveis | 4+ projetos, mais específico de negócio — prioridade menor | Sem ação — já coberto por [`DateService.addBusinessDays`](../DateService/addBusinessDays.md); não implementado nesta versão |
| `SemesterUtil.currentSemesterKey(LocalDate)` | chave de semestre corrente | `eleva_supplies_api/.../points/utils/SemesterUtil.java` | 1 projeto, nicho | ✅ Implementado (v1.1.0) — [`DateUtils.semesterKey`](../DateUtils/semesterKey.md) |

## 6. Coleções / Persistência / IO (todos NOVO)

| Método | O que faz | Projetos / caminhos | Duplicatas | Status |
|---|---|---|---|---|
| `ExcelProcessingUtilService.mapHeaderIndexes/isRowCompletelyEmpty/getDataFromRow/parseDate` | helpers de linha/cabeçalho Apache POI | `eleva_orders_api/.../util/services/ExcelProcessingUtilService.java` (+ eleva-office api/data, elevadocs, eleva-contracts, eleva-helpdesk, eleva-messaging, portal_inova) | 7 projetos, **quase idênticos** — forte candidato | ❌ Pulado — exigiria dependência Apache POI (decisão do usuário: manter a lib enxuta) |
| `StringListConverter` | `AttributeConverter` JPA `List<String>` ↔ CSV | `soluarq-portal-api/.../util/StringListConverter.java` | idioma `AttributeConverter` list↔csv recorre em ~23 arquivos sob nomes variados | ❌ Pulado — exigiria dependência JPA (`javax.persistence`/`jakarta.persistence`), decisão do usuário |
| `FileUtil.encoder(path)` / `moveFileToWorkspace` | arquivo → Base64, mover para workspace | elevaflow, inovadocs/vivo, inovadocs/soluarq, pg, portal-e2e, societario, uso-proprio, venda-imoveis | 9 projetos — **PARCIAL**, sobrepõe parcialmente helpers de `DocumentUtils.getDocumentInBase64` | ✅ Implementado (v1.1.0) — [`FileUtils.toBase64/moveToWorkspace`](../FileUtils/README.md) (reescrito com `java.nio.file`, sem Guava; não força extensão `.pdf`) |
| `ArrayUtil.removeDuplicates(List<String>)` | dedup de lista | elevaflow, uso-proprio/segmento7, venda-imoveis | 3 projetos, trivial (poderia ser só `.distinct()`) — prioridade baixa | ✅ Implementado (v1.1.0) — [`CollectionUtils.removeDuplicates`](../CollectionUtils/removeDuplicates.md) (genérico `<T>`, O(n) com `LinkedHashSet`) |

---

## Recomendação priorizada (multi-projeto + genuinamente novo)

1. **Conversor SMS GSM-7** (`Utf8ToGsm7ConverterService`) — 8+ projetos, autocontido, fácil de portar.
2. **Feriados nacionais + dias úteis via BrasilAPI** — 6 projetos, reinventado sob 5 nomes distintos;
   avaliar se complementa ou substitui a abordagem `jollyday` já usada em `DateService`.
3. **Helpers Excel/POI** (`ExcelProcessingUtilService`) — 7 projetos, cópias quase idênticas.
4. **`RandomUtil` / `EncodeService` / `PasswordUtil.getToken`** — 8–10 projetos cada, utilitários puros sem dependências externas.
5. **`removeAccents` (já existe, ver overlap) + title-case PT-BR + `digitsOnly`/`emailDomain`** —
   clusters `StringUtilService`/`StringFormatUtil`, 24–44 arquivos; grande parte já tem equivalente
   na lib (`StringUtils`), então o trabalho aqui é mais **migração** do que criação.
6. **`PasswordValidator`** (regex de força de senha) — 12 projetos, `NOVO`.
7. **Validação de e-mail, moeda BRL, tamanho em bytes, máscara LGPD** — menor superfície, mas gaps
   limpos e genuinamente novos na lib.

## Overlaps para aposentar (já cobertos pela lib atual)

- **CPF/CNPJ inline**: `Validacao`, `ValidationsUtil.isCPF/isCNPJ`, `DocumentoUtil.formatarCpf/formatarCnpj/limparMascara`,
  `CNPJValidator`, `CnpjValidator`/`CNPJService` local, `CnpjValidatorService` — trocar por
  `CPFService`/`CNPJService` da lib.
- **CEP format/normalize inline**: `StringFormatUtil.cepFormatter`, `StringUtilService.normalizeCep`,
  cluster `LocationService`/`CepService` local/`CepLookupService` (13 arquivos) — trocar por
  `CepUtils`/`CepService` da lib.
- **Formatação de telefone inline**: cluster `PhoneNumberService` local — trocar por
  `PhoneNumberService` da lib (só a allowlist de DDD tem alguma diferença, e a lib já tem uma).
- **Formatação básica de data**: `DateFormat.java`, `DateUtil.java`, `DateService.java` locais —
  trocar por `DateUtils`/`DateService` da lib; só a parte de feriados/dias úteis reimplementada
  (item 5 acima) é valor novo de fato.

> Candidato natural para migração automatizada: agente `UtilsLibIntegrator` (já disponível neste
> ambiente), que troca dependências locais de CPF/CNPJ/telefone/data/CEP pelos equivalentes desta
> lib.

## Escopo desta auditoria

Nenhuma alteração de código Java, `pom.xml` ou versão foi feita como parte deste relatório —
implementação de qualquer item acima é trabalho de uma tarefa futura separada.
