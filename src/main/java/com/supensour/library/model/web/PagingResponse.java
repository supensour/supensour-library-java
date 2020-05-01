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
public class PagingResponse implements Serializable {

  private static final long serialVersionUID = -5763546386061316673L;

  private Long number;

  private Long size;

  private Long numberOfElements;

  private Long totalPages;

  private Long totalElements;

}
