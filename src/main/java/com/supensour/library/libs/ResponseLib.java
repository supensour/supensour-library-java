package com.supensour.library.libs;

import com.supensour.library.model.map.SetValueMap;
import com.supensour.library.model.web.PagingResponse;
import com.supensour.library.model.web.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.function.Function;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class ResponseLib {

  private ResponseLib() {}

  public static <T> Response<T> status(HttpStatus status, T data, PagingResponse page) {
    return Response.<T>builder()
        .data(data)
        .code(status.value())
        .status(status.name())
        .page(page)
        .build();
  }

  public static <T> Response<T> status(HttpStatus status, T data) {
    return status(status, data, null);
  }

  public static <T> Response<T> status(HttpStatus status) {
    return status(status, null);
  }

  public static <T, R> Response<T> status(HttpStatus status, Page<R> page, Function<Page<R>, T> mapper) {
    return status(status, mapper.apply(page), PagingLib.toPagingResponse(page));
  }

  public static <T> Response<T> ok(T data, PagingResponse page) {
    return status(HttpStatus.OK, data, page);
  }

  public static <T> Response<T> ok(T data) {
    return status(HttpStatus.OK, data);
  }

  public static <T> Response<T> ok() {
    return status(HttpStatus.OK, null);
  }

  public static <T, R> Response<T> ok(Page<R> page, Function<Page<R>, T> mapper) {
    return status(HttpStatus.OK, page, mapper);
  }

  public static <T> Response<T> badRequest(SetValueMap<String, String> errors, T data) {
    Response<T> response = ResponseLib.status(HttpStatus.BAD_REQUEST, data);
    response.setErrors(errors);
    return response;
  }

  public static <T> Response<T> badRequest(SetValueMap<String, String> errors) {
    return badRequest(errors, null);
  }

}
