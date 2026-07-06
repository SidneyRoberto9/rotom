# CepService

Pacote: `io.github.sidneyroberto9.rotom.cep.domain`
Arquivo: `src/main/java/io/github/sidneyroberto9/rotom/cep/domain/CepService.java`

Serviço de busca de endereço por CEP com estratégia de **fallback multi-provider**: tenta cada
provider na ordem configurada; se um falhar ou não retornar dado, o próximo é tentado
automaticamente. Providers padrão, em ordem de prioridade: **ViaCEP → OpenCEP → BrasilCEP →
CEP.Rest → Zippopotam**.

## Construtores

```java
CepService cepService = new CepService();
// 5 providers padrão, na ordem acima

CepService cepService = new CepService(List.of(new ViaCepProvider(), new OpenCepProvider()));
// lista customizada de providers, na ordem informada
```

Pacote dos providers: `io.github.sidneyroberto9.rotom.cep.infra.provider` (`ViaCepProvider`,
`OpenCepProvider`, `BrasilCepProvider`, `CepRestProvider`, `ZippopotamProvider`) — todos
implementam a interface pública `CepProvider` (`String name()` + `Optional<Address>
fetch(String cep)`), permitindo providers customizados.

## Bean Spring

Com `RotomAutoConfiguration` ativo, um bean `CepService` (construído com `new CepService()`,
providers padrão) fica disponível para injeção.

## Classe `Address` (DTO de retorno)

`io.github.sidneyroberto9.rotom.cep.domain.Address` — Lombok `@Data @NoArgsConstructor
@AllArgsConstructor`, 13 campos `String`, **todos com nomes em português**:

| Campo | Getter | Significado |
|---|---|---|
| `uf` | `getUf()` | sigla do estado |
| `cep` | `getCep()` | CEP normalizado (sem máscara) |
| `bairro` | `getBairro()` | bairro |
| `localidade` | `getLocalidade()` | cidade |
| `logradouro` | `getLogradouro()` | rua/avenida |
| `complemento` | `getComplemento()` | complemento do endereço |
| `ibge` | `getIbge()` | código IBGE do município |
| `gia` | `getGia()` | código GIA (só alguns estados) |
| `ddd` | `getDdd()` | código de área telefônico |
| `siafi` | `getSiafi()` | código SIAFI |
| `unidade` | `getUnidade()` | unidade (grandes empresas/CEPs especiais) |
| `estado` | `getEstado()` | nome completo do estado |
| `regiao` | `getRegiao()` | região do Brasil |

> Nem todo provider preenche todos os campos — providers mais simples (OpenCEP, CEP.Rest)
> preenchem só `uf, cep, bairro, localidade, logradouro, complemento, ibge`, deixando o resto
> `null`. Zippopotam preenche só `uf, cep, localidade, estado`.

## Métodos

| Método | Retorno | Descrição |
|---|---|---|
| [`lookup(String rawCep)`](lookup.md) | `Address` | busca endereço, tentando cada provider em ordem |
