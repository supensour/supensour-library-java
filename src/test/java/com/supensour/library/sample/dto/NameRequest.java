package com.supensour.library.sample.dto;

import com.supensour.library.sample.validation.ValidName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NameRequest {

  @Valid
  @ValidName(path = {"name", "customNamePath"})
  private Name name;

}
