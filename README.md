# Rotom

[![Maven Central](https://img.shields.io/maven-central/v/io.github.sidneyroberto9/rotom)](https://central.sonatype.com/artifact/io.github.sidneyroberto9/rotom)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-11%2B-orange)](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

Brazilian-focused Java utility library with validators, formatters, CEP lookup, business day calculations, and string helpers. Includes optional Spring Boot auto-configuration.

## Requirements

- Java 11+
- (Optional) Spring Boot 2.7+

## Installation

**Maven**

```xml
<dependency>
    <groupId>io.github.sidneyroberto9</groupId>
    <artifactId>rotom</artifactId>
    <version>1.0.5</version>
</dependency>
```

**Gradle**

```groovy
implementation 'io.github.sidneyroberto9:rotom:1.0.5'
```

## Usage

All services can be instantiated directly or injected via Spring Boot auto-configuration.

---

### CPFService

Validates and formats Brazilian CPF numbers. Accepts masked or unmasked input.

```java
CPFService cpf = new CPFService();

cpf.isValid("529.982.247-25");   // true
cpf.isValid("111.111.111-11");   // false — repeated digits

cpf.format("52998224725");       // "529.982.247-25"
cpf.strip("529.982.247-25");     // "52998224725"
cpf.isFormatted("529.982.247-25"); // true
```

---

### CNPJService

Validates and formats Brazilian CNPJ numbers. Accepts masked or unmasked input.

```java
CNPJService cnpj = new CNPJService();

cnpj.isValid("11.222.333/0001-81");  // true
cnpj.isValid("11.111.111/1111-11");  // false — repeated digits

cnpj.format("11222333000181");       // "11.222.333/0001-81"
cnpj.strip("11.222.333/0001-81");    // "11222333000181"
cnpj.isFormatted("11.222.333/0001-81"); // true
```

---

### CepService

Looks up Brazilian addresses by CEP with automatic multi-provider fallback.

Default provider priority: **ViaCEP → OpenCEP → BrasilCEP → CEP.Rest → Zippopotam**

```java
CepService cepService = new CepService();

Address address = cepService.lookup("58038-000");
// address.getLogradouro() → "Avenida Epitácio Pessoa"
// address.getLocalidade() → "João Pessoa"
// address.getUf()         → "PB"
```

The `Address` object contains: `uf`, `cep`, `bairro`, `localidade`, `logradouro`, `complemento`, `ibge`, `gia`, `ddd`, `siafi`, `unidade`, `estado`, `regiao`.

If no provider returns data, an `Address` with all fields `null` (except `cep`) is returned. Throws `IllegalArgumentException` for CEPs with invalid length or non-numeric characters.

**Custom providers**

```java
CepService cepService = new CepService(List.of(new ViaCepProvider(), new OpenCepProvider()));
```

---

### DateService

Business day calculations using the Brazilian national holiday calendar (jollyday).

```java
DateService dateService = new DateService();

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

---

### PhoneNumberService

Formats and validates Brazilian phone numbers (mobile and landline).

```java
PhoneNumberService phone = new PhoneNumberService();

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

Numbers with country code prefix `+55` or `55` are stripped automatically. Numbers without area code (8 or 9 digits) are accepted.

---

### StringUtils

String manipulation with Brazilian Portuguese support.

```java
StringUtils str = new StringUtils();

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

---

## Spring Boot Auto-Configuration

When Spring Boot is on the classpath, all services are registered as beans automatically — no configuration required.

```java
@Service
public class MyService {

    private final CPFService cpfService;
    private final CNPJService cnpjService;
    private final CepService cepService;
    private final DateService dateService;
    private final PhoneNumberService phoneNumberService;
    private final StringUtils stringUtils;

    public MyService(
            CPFService cpfService,
            CNPJService cnpjService,
            CepService cepService,
            DateService dateService,
            PhoneNumberService phoneNumberService,
            StringUtils stringUtils
    ) {
        this.cpfService = cpfService;
        this.cnpjService = cnpjService;
        this.cepService = cepService;
        this.dateService = dateService;
        this.phoneNumberService = phoneNumberService;
        this.stringUtils = stringUtils;
    }
}
```

## License

MIT — see [LICENSE](https://opensource.org/licenses/MIT).