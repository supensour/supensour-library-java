package com.supensour.library.web.error;

import com.supensour.library.libs.CollectionLib;
import com.supensour.library.model.error.RespondingException;
import com.supensour.library.libs.ErrorLib;
import com.supensour.library.libs.ResponseLib;
import com.supensour.library.model.web.Response;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public interface BaseErrorControllerHandler {

  Logger getLogger();

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  default Response<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
    getLogger().warn(getMessage(e), e);
    Map<String, List<String>> errors = ErrorLib.mapFromBindingResult(e.getBindingResult());
    return ResponseLib.badRequest(errors);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  default Response<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    getLogger().warn(getMessage(e), e);
    Map<String, List<String>> errors = CollectionLib.addToMultiValueMap(HashMap::new, e.getName(), e.getMessage());
    return ResponseLib.badRequest(errors);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  default Response<?> bindingException(BindException e) {
    getLogger().warn(getMessage(e), e);
    Map<String, List<String>> errors = ErrorLib.mapFromBindingResult(e.getBindingResult());
    return ResponseLib.badRequest(errors);
  }

  @ExceptionHandler(RespondingException.class)
  default Response<?> respondingException(RespondingException e, HttpServletResponse response) {
    if (e.getStatus().is5xxServerError()) {
      getLogger().error(getMessage(e), e);
    } else {
      getLogger().warn(getMessage(e), e);
    }
    response.setStatus(e.getStatus().value());
    return e.toResponse();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoHandlerFoundException.class)
  default Response<?> noHandlerFoundException(NoHandlerFoundException e) {
    getLogger().warn(getMessage(e), e);
    return ResponseLib.status(HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  default Response<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    getLogger().warn(getMessage(e), e);
    return ResponseLib.status(HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  default Response<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
    getLogger().warn(getMessage(e), e);
    return ResponseLib.status(HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  default Response<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
    getLogger().warn(getMessage(e), e);
    return ResponseLib.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
  default Response<?> httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
    getLogger().warn(getMessage(e), e);
    return ResponseLib.status(HttpStatus.NOT_ACCEPTABLE);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Throwable.class)
  default Response<?> throwable(Throwable e) {
    getLogger().error(getMessage(e), e);
    Response<?> response = ResponseLib.status(HttpStatus.INTERNAL_SERVER_ERROR);
    if (StringUtils.hasText(e.getMessage())) {
      response.addError("default", e.getMessage());
    }
    return response;
  }

  default String getMessage(Throwable e) {
    return String.format("%s: %s", e.getClass().getName(), e.getMessage());
  }

}
