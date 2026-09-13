# Rotom

[![Maven Central](https://img.shields.io/maven-central/v/io.github.sidneyroberto9/rotom)](https://central.sonatype.com/artifact/io.github.sidneyroberto9/rotom)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-11%2B-orange)](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

Java utilities for Brazilian applications — CPF/CNPJ validation, CEP lookup with provider fallback,
business days, phone numbers, and formatting. Zero configuration on Spring Boot.

## Why Rotom

Every Brazilian Java project ends up re-writing the same code: the CPF check digit, the CNPJ check
digit, the `(83) 98663-5812` mask, "is this a business day", and a call to ViaCEP. It gets copied
between projects, drifts, and breaks in a different way each time.

Rotom is that layer packaged once — with the part nobody copies correctly: **CEP lookup does not
depend on a single provider being up.**

## CEP lookup with provider fallback

Public CEP APIs go down, rate limit, and return empty payloads for valid postal codes. A single
hardcoded call to ViaCEP is one outage away from breaking address autofill in production.

`RotomCepService` queries five independent providers in priority order and returns the first
usable answer. A provider that throws, times out, or returns no data is skipped, not propagated:

```
lookup("58038-000")
      │
      ▼
  ViaCEP ────────── address? ──► return
      │ error / empty
      ▼
  OpenCEP ───────── address? ──► return
      │ error / empty
      ▼
  BrasilCEP ─────── address? ──► return
      │ error / empty
      ▼
  CEP.Rest ──────── address? ──► return
      │ error / empty
      ▼
  Zippopotam ────── address? ──► return
      │ error / empty
      ▼
  Address with only the CEP filled in
```

```java
RotomCepService cepService = new RotomCepService();

Address address = cepService.lookup("58038-000");
// address.getLogradouro() → "Avenida Epitácio Pessoa"
// address.getLocalidade() → "João Pessoa"
// address.getUf()         → "PB"
```

A malformed CEP fails fast with `IllegalArgumentException` — only transport and provider failures
fall through the chain. When every provider fails, the result is an `Address` with all fields
`null` except the CEP itself, so callers never have to handle an exception for "not found".

The order is a constructor argument, and `CepProvider` is a two-method interface (`name()`,
`fetch(cep)`), so an internal or paid provider can lead the chain:

```java
RotomCepService cepService = new RotomCepService(List.of(new MyInternalProvider(), new ViaCepProvider()));
```

`Address` carries `uf`, `cep`, `bairro`, `localidade`, `logradouro`, `complemento`, `ibge`, `gia`,
`ddd`, `siafi`, `unidade`, `estado`, and `regiao` — normalized across providers by a mapper per
provider, so the shape does not change depending on who answered.

## Installation

**Maven**

```xml
<dependency>
    <groupId>io.github.sidneyroberto9</groupId>
    <artifactId>rotom</artifactId>
    <version>2.0.0</version>
</dependency>
```

**Gradle**

```groovy
implementation 'io.github.sidneyroberto9:rotom:2.0.0'
```

## Quick start

Every service is a plain object — `new` it, or inject it on Spring Boot (see
[Spring Boot integration](#spring-boot-integration)).

```java
RotomCPFService cpf = new RotomCPFService();
cpf.isValid("529.982.247-25");   // true
cpf.format("52998224725");       // "529.982.247-25"

RotomDateService dates = new RotomDateService();
dates.addBusinessDays(LocalDate.of(2025, 6, 9), 3);  // skips weekends and holidays

RotomCepService cep = new RotomCepService();
cep.lookup("58038-000").getLocalidade();             // "João Pessoa"
```

## Modules

| Module | Type | What it does |
|--------|------|--------------|
| CPF | `RotomCPFService` | Validates, formats, and strips CPF numbers |
| CNPJ | `RotomCNPJService` | Validates, formats, and strips CNPJ numbers |
| CEP | `RotomCepService` | Address lookup with five-provider fallback |
| Business days | `RotomDateService` | Holidays and business-day arithmetic (jollyday, Brazilian calendar) |
| Holidays (live) | `RotomBrasilApiHolidayService` | National holidays for a year straight from BrasilAPI |
| Dates | `RotomDateUtils` | Conversions between `Date`, `LocalDate`, `LocalDateTime`; weekend checks |
| Durations | `RotomDurationUtils` | Days/hours between dates, humanized durations |
| Phone | `RotomPhoneNumberService` | Validates, formats, and classifies mobile vs landline |
| Strings | `RotomStringUtils` | Accents, slugs, capitalization, email masking, null-safety |
| Masks | `RotomMaskUtils` | Partially masks CPF and CNPJ for display |
| Money | `RotomMoneyUtils` | BRL formatting |
| Numbers | `RotomNumberUtils` | Safe numeric type checks on strings |
| Validation | `RotomEmailValidator`, `RotomPasswordValidator` | Email format, password strength |
| Hashing | `RotomHashUtils` | MD5/SHA-256/SHA-512, HMAC verification |
| Crypto | `RotomCryptoUtils` | Symmetric encrypt/decrypt |
| Encoding | `RotomEncodingUtils` | Base64 and integer encoding |
| Random | `RotomRandomUtil` | Tokens, API keys, codes, hex, alphanumerics |
| Files | `RotomFileUtils` | Base64, size formatting, safe names, workspace moves |
| HTTP | `RotomHttpUtils` | Client IP resolution behind proxies |
| SMS | `RotomGsm7Converter` | Converts text to the GSM 7-bit alphabet |
| Collections | `RotomCollectionUtils` | Duplicate removal preserving order |

The sections below cover the modules that carry Brazil-specific rules. The rest are small enough to
read from the Javadoc.

### CPF

Validates and formats Brazilian CPF numbers. Accepts masked or unmasked input.

```java
RotomCPFService cpf = new RotomCPFService();

cpf.isValid("529.982.247-25");   // true
cpf.isValid("111.111.111-11");   // false — repeated digits

cpf.format("52998224725");       // "529.982.247-25"
cpf.strip("529.982.247-25");     // "52998224725"
cpf.isFormatted("529.982.247-25"); // true
```

### CNPJ

Validates and formats Brazilian CNPJ numbers. Accepts masked or unmasked input.

```java
RotomCNPJService cnpj = new RotomCNPJService();

cnpj.isValid("11.222.333/0001-81");  // true
cnpj.isValid("11.111.111/1111-11");  // false — repeated digits

cnpj.format("11222333000181");       // "11.222.333/0001-81"
cnpj.strip("11.222.333/0001-81");    // "11222333000181"
cnpj.isFormatted("11.222.333/0001-81"); // true
```

### Business days

Business day calculations over the Brazilian national holiday calendar (jollyday). The calendar and
timezone are constructor arguments, so a state or municipal calendar can replace the default.

```java
RotomDateService dateService = new RotomDateService();

LocalDate date = LocalDate.of(2025, 6, 9); // Monday

dateService.isHoliday(date);       // false
dateService.isBusinessDay(date);   // true

dateService.addBusinessDays(date, 3);      // skips weekends and holidays
dateService.subtractBusinessDays(date, 2);

dateService.adjustToNextBusinessDay(date);     // returns date if already a business day
dateService.adjustToPreviousBusinessDay(date);

dateService.getFirstBusinessDayOfMonth(2025, 1); // 2025-01-02 (Jan 1 is a holiday)
dateService.getLastBusinessDayOfMonth(2025, 1);  // 2025-01-31

dateService.countBusinessDays(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31)); // 21
```

All methods are overloaded for `LocalDate`, `LocalDateTime`, and `java.util.Date`.

`RotomBrasilApiHolidayService` is the online counterpart: it fetches the national holidays of a year
from BrasilAPI instead of using the embedded calendar, at the cost of an HTTP call.

### Phone numbers

Formats and validates Brazilian phone numbers (mobile and landline).

```java
RotomPhoneNumberService phone = new RotomPhoneNumberService();

phone.isValid("83986635812");           // true
phone.isMobile("83986635812");          // true
phone.isLandline("8332221234");         // true

phone.format("83986635812");            // "(83) 98663-5812"
phone.format("8332221234");             // "(83) 3222-1234"
phone.formatWithCountryCode("83986635812"); // "+55 (83) 98663-5812"

phone.strip("(83) 98663-5812");         // "83986635812"
phone.getDDD("83986635812");            // "83"
phone.isFormatted("(83) 98663-5812");   // true
```

Numbers with country code prefix `+55` or `55` are stripped automatically. Numbers without area code
(8 or 9 digits) are accepted.

### Strings

String manipulation with Brazilian Portuguese support.

```java
RotomStringUtils str = new RotomStringUtils();

// Null-safety
str.isBlank(null);          // true
str.isNotBlank("hello");    // true
str.trimOrNull("  ");       // null
str.requireNonBlank("", "field required"); // throws IllegalArgumentException

// Capitalization
str.capitalize("hello");                          // "Hello"
str.capitalizeWords("joão da silva");             // "João da Silva"
str.firstTwoNames("João da Silva Souza");         // "João Da"

// Extraction
str.digitsOnly("CPF: 123.456.789-09");            // "12345678909"
str.alphanumericOnly("hello, world!");             // "helloworld"

// Normalization
str.removeAccents("café");                         // "cafe"
str.slugify("Olá Mundo!");                         // "ola-mundo"
str.truncate("hello world", 5);                    // "hello"
str.containsIgnoreCase("Hello World", "world");    // true

// Email
str.normalizeEmail("  USER@GMAIL.COM  ");          // "user@gmail.com"
str.emailDomain("user@gmail.com");                 // "GMAIL"
str.maskEmail("user@gmail.com");                   // "u***@gmail.com"

// URL
str.encodeUrl("https://example.com/search?q=", "hello world");
// "https://example.com/search?q=hello+world"
```

## Spring Boot integration

With Spring Boot on the classpath, `RotomAutoConfiguration` registers every service as a bean — no
component scan, no `@Import`, no properties. Each bean is `@ConditionalOnMissingBean`, so replacing
one (a `RotomCepService` with a custom provider chain, for example) is a matter of declaring your
own.

```java
@Service
public class MyService {

    private final RotomCPFService cpfService;
    private final RotomCepService cepService;
    private final RotomDateService dateService;

    public MyService(
            RotomCPFService cpfService,
            RotomCepService cepService,
            RotomDateService dateService
    ) {
        this.cpfService = cpfService;
        this.cepService = cepService;
        this.dateService = dateService;
    }
}
```

## Testing

JUnit 5, 35 test classes covering the check-digit algorithms, the provider fallback chain against
stubbed HTTP responses, business-day edge cases around holidays, and the formatting utilities.

```bash
mvn test
```

## Compatibility

| Component | Supported version |
|-----------|-------------------|
| Rotom | 2.0.x |
| Java | 11+ |
| Spring Boot | 2.7+ (optional — the library works without Spring) |

## License

MIT — see [LICENSE](https://opensource.org/licenses/MIT).
