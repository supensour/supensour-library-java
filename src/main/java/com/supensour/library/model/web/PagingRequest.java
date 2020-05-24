package com.supensour.library.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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

}
