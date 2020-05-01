package com.supensour.library.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PagingRequest {

  private Long number;

  private Long size;

  @Singular
  private List<SortingRequest> sorts;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder(toBuilder = true)
  public static class SortingRequest {

    private Sort.Direction direction;

    private String field;

    @Builder.Default
    private Boolean ignoreCase = Boolean.FALSE;

    @Builder.Default
    private Sort.NullHandling nullHandling = Sort.NullHandling.NATIVE;

  }

}
