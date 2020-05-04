package com.supensour.library.model.error;

import com.supensour.library.libs.ResponseLib;
import com.supensour.library.model.web.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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
public class RespondingException extends RuntimeException implements ErrorMapping<RespondingException> {

  private static final long serialVersionUID = -7055426232483477828L;

  private Object data;

  private HttpStatus status;

  @Builder.Default
  private Map<String, List<String>> errors = new HashMap<>();

  public Response<?> toResponse() {
    Response<?> response = ResponseLib.status(status, data);
    response.setErrors(errors);
    return response;
  }

}
