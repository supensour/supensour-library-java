package com.supensour.library.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PagingRequest implements Serializable {

  private static final long serialVersionUID = 1316713092231797833L;

  private Long number;

  private Long size;

  @Singular
  private List<SortingRequest> sorts;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder(toBuilder = true)
  public static class SortingRequest implements Serializable {

    private static final long serialVersionUID = 7673365581251266984L;

    private Sort.Direction direction;

    private String field;

    @Builder.Default
    private Boolean ignoreCase = Boolean.FALSE;

    @Builder.Default
    private Sort.NullHandling nullHandling = Sort.NullHandling.NATIVE;

  }

}
