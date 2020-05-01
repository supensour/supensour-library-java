package com.supensour.library.model.web;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class PagingResponseTest {

  @Test
  public void data() {
    PagingResponse pagingResponse = new PagingResponse();
    pagingResponse.setNumber(0L);
    pagingResponse.setSize(10L);
    pagingResponse.setNumberOfElements(8L);
    pagingResponse.setTotalPages(1L);
    pagingResponse.setTotalElements(8L);
    assertEquals(0L, pagingResponse.getNumber().longValue());
    assertEquals(10L, pagingResponse.getSize().longValue());
    assertEquals(8L, pagingResponse.getNumberOfElements().longValue());
    assertEquals(1L, pagingResponse.getTotalPages().longValue());
    assertEquals(8L, pagingResponse.getTotalElements().longValue());
  }

  @Test
  public void builder() {
    PagingResponse pagingResponse = PagingResponse.builder()
        .number(0L)
        .size(10L)
        .numberOfElements(8L)
        .totalPages(1L)
        .totalElements(8L)
        .build();
    assertEquals(0L, pagingResponse.getNumber().longValue());
    assertEquals(10L, pagingResponse.getSize().longValue());
    assertEquals(8L, pagingResponse.getNumberOfElements().longValue());
    assertEquals(1L, pagingResponse.getTotalPages().longValue());
    assertEquals(8L, pagingResponse.getTotalElements().longValue());
  }

}
