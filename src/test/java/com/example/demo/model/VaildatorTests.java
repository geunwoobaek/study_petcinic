package com.example.demo.model;

import com.example.demo.model.common.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class VaildatorTests {
    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean=new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;

    }

    @Test
    void shouldNOtValidateWhenFirstNameEmpty(){
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        Person person=new Person();
        person.setFirstName("");
        person.setLastName("smith");
        Validator validator=createValidator();
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        assertThat(constraintViolations).hasSize(1);
        ConstraintViolation<Person> violation = constraintViolations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("firstName");
    }
}
