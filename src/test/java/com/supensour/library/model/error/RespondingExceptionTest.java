package com.supensour.library.model.error;

import com.supensour.library.libs.CollectionLib;
import com.supensour.library.model.web.Response;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class RespondingExceptionTest {

  @Test(expected = RespondingException.class)
  public void testData() {
    RespondingException RespondingException = new RespondingException();
    RespondingException.setData("Data");
    RespondingException.setStatus(HttpStatus.OK);
    RespondingException.setErrors(null);

    assertEquals("Data", RespondingException.getData());
    assertEquals(HttpStatus.OK, RespondingException.getStatus());
    assertNull(RespondingException.getErrors());
    throw RespondingException;
  }

  @Test(expected = RespondingException.class)
  public void testBuilder() {
    RespondingException respondingException = RespondingException.builder()
        .data("Test")
        .status(HttpStatus.OK)
        .build();
    assertEquals("Test", respondingException.getData());
    assertEquals(HttpStatus.OK, respondingException.getStatus());
    assertNotNull(respondingException.getErrors());
    assertEquals(0, respondingException.getErrors().size());
    throw respondingException;
  }

  @Test(expected = RespondingException.class)
  public void testDefaultConstructor() {
    RespondingException respondingException = new RespondingException();
    assertNull(respondingException.getData());
    assertNull(respondingException.getStatus());
    assertNotNull(respondingException.getErrors());
    assertEquals(0, respondingException.getErrors().size());
    throw respondingException;
  }

  @Test(expected = RespondingException.class)
  public void testAddError() {
    RespondingException respondingException = new RespondingException()
        .addError("key", "Value1")
        .addError("key", "Value2");
    assertNotNull(respondingException.getErrors());
    assertEquals(1, respondingException.getErrors().size());
    assertEquals(2, respondingException.getErrors().get("key").size());
    assertEquals(CollectionLib.toSet("Value1", "Value2"), respondingException.getErrors().get("key"));
    throw respondingException;
  }

  @Test
  public void testToResponse() {
    RespondingException respondingException = RespondingException.builder()
        .data("data")
        .status(HttpStatus.BAD_REQUEST)
        .build()
        .addError("key", "Invalid");
    Response<?> response = respondingException.toResponse();
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getCode().intValue());
    assertEquals(HttpStatus.BAD_REQUEST.name(), response.getStatus());
    assertEquals("data", response.getData());
    assertEquals(1, response.getErrors().size());
    assertEquals(CollectionLib.toSet("Invalid"), response.getErrors().get("key"));
  }

}
