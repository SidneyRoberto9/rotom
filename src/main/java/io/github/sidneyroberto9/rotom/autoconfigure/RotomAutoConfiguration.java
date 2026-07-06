package io.github.sidneyroberto9.rotom.autoconfigure;

import io.github.sidneyroberto9.rotom.cep.domain.CepService;
import io.github.sidneyroberto9.rotom.cep.domain.CepUtils;
import io.github.sidneyroberto9.rotom.cnpj.CNPJService;
import io.github.sidneyroberto9.rotom.collections.CollectionUtils;
import io.github.sidneyroberto9.rotom.cpf.CPFService;
import io.github.sidneyroberto9.rotom.date.DateService;
import io.github.sidneyroberto9.rotom.date.DateUtils;
import io.github.sidneyroberto9.rotom.date.DurationUtils;
import io.github.sidneyroberto9.rotom.date.holiday.BrasilApiHolidayService;
import io.github.sidneyroberto9.rotom.encoding.EncodingUtils;
import io.github.sidneyroberto9.rotom.hash.HashUtils;
import io.github.sidneyroberto9.rotom.io.FileUtils;
import io.github.sidneyroberto9.rotom.mask.MaskUtils;
import io.github.sidneyroberto9.rotom.money.MoneyUtils;
import io.github.sidneyroberto9.rotom.phoneNumber.PhoneNumberService;
import io.github.sidneyroberto9.rotom.random.RandomUtil;
import io.github.sidneyroberto9.rotom.sms.Gsm7Converter;
import io.github.sidneyroberto9.rotom.strings.StringUtils;
import io.github.sidneyroberto9.rotom.validation.EmailValidator;
import io.github.sidneyroberto9.rotom.validation.NumberUtils;
import io.github.sidneyroberto9.rotom.validation.PasswordValidator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class RotomAutoConfiguration {

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

    @Bean
    @ConditionalOnMissingBean
    public RandomUtil randomUtil() {
        return new RandomUtil();
    }

    @Bean
    @ConditionalOnMissingBean
    public EncodingUtils encodingUtils() {
        return new EncodingUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public HashUtils hashUtils() {
        return new HashUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public Gsm7Converter gsm7Converter() {
        return new Gsm7Converter();
    }

    @Bean
    @ConditionalOnMissingBean
    public EmailValidator emailValidator() {
        return new EmailValidator();
    }

    @Bean
    @ConditionalOnMissingBean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }

    @Bean
    @ConditionalOnMissingBean
    public NumberUtils numberUtils() {
        return new NumberUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public MoneyUtils moneyUtils() {
        return new MoneyUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public FileUtils fileUtils() {
        return new FileUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public MaskUtils maskUtils() {
        return new MaskUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public DurationUtils durationUtils() {
        return new DurationUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public BrasilApiHolidayService brasilApiHolidayService() {
        return new BrasilApiHolidayService();
    }

    @Bean
    @ConditionalOnMissingBean
    public CollectionUtils collectionUtils() {
        return new CollectionUtils();
    }
}
