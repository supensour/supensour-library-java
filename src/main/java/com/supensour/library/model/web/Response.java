package com.supensour.library.model.web;

import com.supensour.library.model.map.impl.SetValueHashMap;
import com.supensour.library.model.map.SetValueMap;
import com.supensour.library.model.error.ErrorMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private SetValueMap<String, String> errors = new SetValueHashMap<>();

  private PagingResponse page;

}
