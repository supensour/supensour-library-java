package com.supensour.library.sample.controller;

import com.supensour.library.model.group.Pair;
import com.supensour.library.sample.dto.Name;
import com.supensour.library.sample.dto.Numbers;
import com.supensour.library.model.error.RespondingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.Valid;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
@RestController
public class TestController {

  @PostMapping(value = "/validate",
               consumes = MediaType.APPLICATION_JSON_VALUE,
               produces = MediaType.APPLICATION_JSON_VALUE)
  public Name validate(@Valid @RequestBody Name name) {
    return name;
  }

  @GetMapping(value = "/number-pair", produces = MediaType.APPLICATION_JSON_VALUE)
  public Pair<Integer, Long> getNumber(@RequestParam Integer number1, @RequestParam Long number2) {
    return Pair.of(number1, number2);
  }

  @GetMapping(value = "/numbers", produces = MediaType.APPLICATION_JSON_VALUE)
  public Numbers getNumbers(Numbers numbers) {
    return numbers;
  }

  @GetMapping(value = "/not-found", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object notFound() throws Throwable {
    throw new NoHandlerFoundException("GET", "/not-found", HttpHeaders.EMPTY);
  }

  @GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
  public String html() {
    return "<body>Hi</body>";
  }

  @GetMapping(value = "/responding-exception", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object respondingException(@RequestParam Integer code) {
    throw RespondingException.builder()
        .status(HttpStatus.valueOf(code))
        .build()
        .addError("aKey", "aValue");
  }

  @GetMapping(value = "/exception", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object exception(@RequestParam(required = false) String message) throws Throwable {
    throw new Exception(message);
  }

}
