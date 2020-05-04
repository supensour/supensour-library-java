package com.supensour.library.libs;

import com.supensour.library.model.map.MultiValueMap;
import com.supensour.library.model.map.impl.MultiValueHashMap;
import com.supensour.library.sample.dto.Name;
import com.supensour.library.sample.dto.NameRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class ErrorLibTest {

  private Validator validator;

  @Before
  public void setUp() {
    LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
    MessageInterpolatorFactory messageInterpolatorFactory = new MessageInterpolatorFactory();
    localValidatorFactoryBean.setMessageInterpolator(messageInterpolatorFactory.getObject());
    localValidatorFactoryBean.afterPropertiesSet();
    validator = localValidatorFactoryBean.getValidator();
  }

  @Test
  public void mapFromBindingResult() {
    BindingResult bindingResult = new BeanPropertyBindingResult(null, "nameRequest");
    bindingResult.addError(new FieldError("nameRequest", "firstName", "Blank"));
    bindingResult.addError(new FieldError("nameRequest", "lastName", "Blank"));

    Map<String, List<String>> errors = ErrorLib.mapFromBindingResult(bindingResult);
    assertFalse(errors.isEmpty());
    assertEquals(2, errors.size());
    assertEquals(1, errors.get("firstName").size());
    assertEquals("Blank", errors.get("firstName").get(0));
    assertEquals(1, errors.get("lastName").size());
    assertEquals("Blank", errors.get("lastName").get(0));
  }

  @Test
  public void mapFromBindingResult_empty() {
    BindingResult bindingResult = new BeanPropertyBindingResult(null, "nameRequest");
    Map<String, List<String>> errors = ErrorLib.mapFromBindingResult(bindingResult);
    assertTrue(errors.isEmpty());
  }

  @Test
  public void mapFromConstraintViolations() {
    NameRequest nameRequest = new NameRequest(new Name("  \t\n  ", ""));
    Set<ConstraintViolation<NameRequest>> violations = validator.validate(nameRequest);
    Map<String, List<String>> errors = ErrorLib.mapFromConstraintViolations(violations);

    MultiValueMap<String, String> expectation = new MultiValueHashMap<>();
    expectation.set("name", "InvalidName");
    expectation.set("customNamePath", "InvalidName");
    expectation.set("name.firstName", "Blank");
    expectation.set("name.lastName", "Blank");

    assertFalse(violations.isEmpty());
    assertFalse(errors.isEmpty());
    assertEquals(4, errors.size());
    assertEquals(expectation, errors);
  }

  @Test
  public void mapFromConstraintViolations_empty() {
    NameRequest nameRequest = new NameRequest(new Name("Suprayan", "Yapura"));
    Set<ConstraintViolation<NameRequest>> violations = validator.validate(nameRequest);

    assertTrue(violations.isEmpty());
    Map<String, List<String>> errors = ErrorLib.mapFromConstraintViolations(violations);
    assertTrue(errors.isEmpty());
  }

}
