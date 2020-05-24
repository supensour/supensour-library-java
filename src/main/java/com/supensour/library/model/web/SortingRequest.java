package com.supensour.library.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SortingRequest implements Serializable {

  private static final long serialVersionUID = 7673365581251266984L;

  private Sort.Direction direction;

  private String field;

  @Builder.Default
  private Boolean ignoreCase = Boolean.FALSE;

  @Builder.Default
  private Sort.NullHandling nullHandling = Sort.NullHandling.NATIVE;

}
