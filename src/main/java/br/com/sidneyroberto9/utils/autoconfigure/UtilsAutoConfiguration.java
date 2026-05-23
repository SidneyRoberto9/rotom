package br.com.sidneyroberto9.utils.autoconfigure;

import br.com.sidneyroberto9.utils.cep.domain.CepService;
import br.com.sidneyroberto9.utils.cep.domain.CepUtils;
import br.com.sidneyroberto9.utils.cnpj.CNPJService;
import br.com.sidneyroberto9.utils.cpf.CPFService;
import br.com.sidneyroberto9.utils.date.DateService;
import br.com.sidneyroberto9.utils.date.DateUtils;
import br.com.sidneyroberto9.utils.phoneNumber.PhoneNumberService;
import br.com.sidneyroberto9.utils.strings.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CepUtils cepUtils() {
        return new CepUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public CepService cepService() {
        return new CepService();
    }

    @Bean
    @ConditionalOnMissingBean
    public CPFService cpfService() {
        return new CPFService();
    }

    @Bean
    @ConditionalOnMissingBean
    public CNPJService cnpjService() {
        return new CNPJService();
    }

    @Bean
    @ConditionalOnMissingBean
    public PhoneNumberService phoneNumberService() {
        return new PhoneNumberService();
    }

    @Bean
    @ConditionalOnMissingBean
    public DateUtils dateUtils() {
        return new DateUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public DateService dateService() {
        return new DateService();
    }

    @Bean
    @ConditionalOnMissingBean
    public StringUtils stringUtils() {
        return new StringUtils();
    }
}
