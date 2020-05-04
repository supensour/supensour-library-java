package com.supensour.library.model.web;

import com.supensour.library.model.error.ErrorMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Response<T> implements ErrorMapping<Response<T>> {

  private static final long serialVersionUID = -4035329996311901335L;

  private Integer code;

  private String status;

  private T data;

  @Builder.Default
  private Map<String, List<String>> errors = new HashMap<>();

  private PagingResponse page;

}
