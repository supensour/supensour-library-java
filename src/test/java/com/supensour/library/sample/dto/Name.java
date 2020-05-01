package com.supensour.library.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Name {

  @NotBlank(message = "Blank")
  private String firstName;

  @NotBlank(message = "Blank")
  private String lastName;

}
