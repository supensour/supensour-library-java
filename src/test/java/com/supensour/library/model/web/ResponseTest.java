package com.supensour.library.model.web;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class ResponseTest {

  @Test
  public void data() {
    Response<String> response = new Response<>();
    response.setCode(200);
    response.setStatus("OK");
    response.setData("Data");
    response.setErrors(null);
    response.setPage(new PagingResponse());
    assertEquals(200, response.getCode().intValue());
    assertEquals("OK", response.getStatus());
    assertEquals("Data", response.getData());
    assertNull(response.getErrors());
    assertNotNull(response.getPage());
  }

  @Test
  public void builder() {
    Response<String> response = Response.<String>builder()
        .code(200)
        .status("OK")
        .data("Data")
        .build();
    assertEquals(200, response.getCode().intValue());
    assertEquals("OK", response.getStatus());
    assertEquals("Data", response.getData());
    assertNotNull(response.getErrors());
    assertNull(response.getPage());
  }

}
