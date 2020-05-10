package com.supensour.library.web.error;

import com.supensour.library.libs.CollectionLib;
import com.supensour.library.sample.TestApplication;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class BaseErrorControllerHandlerTest {

  @Value("${local.server.port}")
  private int port;

  @Before
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void methodArgumentNotValidException() {
    given()
        .body("{}")
        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/validate")
        .then()
        .body("code", equalTo(HttpStatus.BAD_REQUEST.value()))
        .body("status", equalTo(HttpStatus.BAD_REQUEST.name()))
        .body("errors.firstName[0]", equalTo("Blank"))
        .body("errors.lastName[0]", equalTo("Blank"))
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void methodArgumentTypeMismatchException() {
    given()
        .param("number1", "2")
        .param("number2", "a number")
        .when()
        .get("/number-pair")
        .then()
        .body("code", equalTo(HttpStatus.BAD_REQUEST.value()))
        .body("status", equalTo(HttpStatus.BAD_REQUEST.name()))
        .body("errors.number2[0]", notNullValue(String.class))
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void bindingException() {
    given()
        .param("number1", "2")
        .param("number2", "a number")
        .when()
        .get("/numbers")
        .then()
        .body("code", equalTo(HttpStatus.BAD_REQUEST.value()))
        .body("status", equalTo(HttpStatus.BAD_REQUEST.name()))
        .body("errors.number2[0]", notNullValue(String.class))
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void respondingException_OK() {
    given()
        .param("code", HttpStatus.OK.value())
        .when()
        .get("/responding-exception")
        .then()
        .body("code", equalTo(HttpStatus.OK.value()))
        .body("status", equalTo(HttpStatus.OK.name()))
        .body("errors.aKey", equalTo(CollectionLib.toList("aValue")))
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void respondingException_BAD_REQUEST() {
    given()
        .param("code", HttpStatus.BAD_REQUEST.value())
        .when()
        .get("/responding-exception")
        .then()
        .body("code", equalTo(HttpStatus.BAD_REQUEST.value()))
        .body("status", equalTo(HttpStatus.BAD_REQUEST.name()))
        .body("errors.aKey", equalTo(CollectionLib.toList("aValue")))
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void respondingException_BAD_GATEWAY() {
    given()
        .param("code", HttpStatus.BAD_GATEWAY.value())
        .when()
        .get("/responding-exception")
        .then()
        .body("code", equalTo(HttpStatus.BAD_GATEWAY.value()))
        .body("status", equalTo(HttpStatus.BAD_GATEWAY.name()))
        .body("errors.aKey", equalTo(CollectionLib.toList("aValue")))
        .statusCode(HttpStatus.BAD_GATEWAY.value());
  }

  @Test
  public void noHandlerFoundException() {
    given()
        .when()
        .get("/not-found")
        .then()
        .body("code", equalTo(HttpStatus.NOT_FOUND.value()))
        .body("status", equalTo(HttpStatus.NOT_FOUND.name()))
        .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void httpRequestMethodNotSupportedException() {
    given()
        .when()
        .put("/numbers")
        .then()
        .body("code", equalTo(HttpStatus.METHOD_NOT_ALLOWED.value()))
        .body("status", equalTo(HttpStatus.METHOD_NOT_ALLOWED.name()))
        .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
  }

  @Test
  public void httpMessageNotReadableException() {
    given()
        .body("invalid-value")
        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/validate")
        .then()
        .body("code", equalTo(HttpStatus.BAD_REQUEST.value()))
        .body("status", equalTo(HttpStatus.BAD_REQUEST.name()))
        .statusCode(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  public void httpMediaTypeNotSupportedException() {
    given()
        .body("invalid-value")
        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
        .header("Content-Type", MediaType.TEXT_PLAIN_VALUE)
        .when()
        .post("/validate")
        .then()
        .body("code", equalTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
        .body("status", equalTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name()))
        .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
  }

  @Test
  public void httpMediaTypeNotAcceptableException() {
    given()
        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get("/html")
        .then()
        .body("code", equalTo(HttpStatus.NOT_ACCEPTABLE.value()))
        .body("status", equalTo(HttpStatus.NOT_ACCEPTABLE.name()))
        .statusCode(HttpStatus.NOT_ACCEPTABLE.value());
  }

  @Test
  public void exception() {
    given()
        .when()
        .get("/exception")
        .then()
        .body("code", equalTo(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .body("status", equalTo(HttpStatus.INTERNAL_SERVER_ERROR.name()))
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
  }

  @Test
  public void exceptionWithMessage() {
    String errorMessage = "This is error message!";
    Map<String, List<String>> errors = new HashMap<>();
    errors.computeIfAbsent("default", k -> new ArrayList<>()).add(errorMessage);

    given()
        .queryParam("message", errorMessage)
        .when()
        .get("/exception")
        .then()
        .body("code", equalTo(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .body("status", equalTo(HttpStatus.INTERNAL_SERVER_ERROR.name()))
        .body("errors", equalTo(errors))
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
  }

}
