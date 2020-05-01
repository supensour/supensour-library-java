package com.supensour.library.libs;

import com.supensour.library.model.map.impl.SetValueHashMap;
import com.supensour.library.model.map.SetValueMap;
import com.supensour.library.model.web.PagingResponse;
import com.supensour.library.model.web.Response;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class ResponseLibTest {

  private final static Long NUMBER = 0L;
  private final static Long SIZE = 20L;
  private final static Long NUMBER_OF_ELEMENTS = 2L;
  private final static Long TOTAL_ELEMENTS = 2L;
  private final static Long TOTAL_PAGES = 1L;
  private final static String CONTENT_1 = "1";
  private final static String CONTENT_2 = "2";


  @Test
  public void status_data_page() {
    Response<List<String>> response = ResponseLib.status(HttpStatus.OK, createContent(), createPageResponse());
    assertStatus(response, HttpStatus.OK);
    assertPageResponse(response.getPage());
    assertContentString(response.getData());
  }

  @Test
  public void status_data() {
    Response<List<String>> response = ResponseLib.status(HttpStatus.OK, createContent());
    assertStatus(response, HttpStatus.OK);
    assertContentString(response.getData());
  }

  @Test
  public void status() {
    Response<String> response = ResponseLib.status(HttpStatus.OK);
    assertStatus(response, HttpStatus.OK);
  }

  @Test
  public void status_page_mapper() {
    Response<List<Integer>> response = ResponseLib.status(HttpStatus.OK, createPage(), this::parseInts);
    assertStatus(response, HttpStatus.OK);
    assertPageResponse(response.getPage());
    assertContentInteger(response.getData());
  }

  @Test
  public void ok_data_page() {
    Response<Boolean> response = ResponseLib.ok(true, createPageResponse());
    assertStatus(response, HttpStatus.OK);
    assertPageResponse(response.getPage());
    assertTrue(response.getData());
  }

  @Test
  public void ok_data() {
    Response<Boolean> response = ResponseLib.ok(true);
    assertStatus(response, HttpStatus.OK);
    assertTrue(response.getData());
  }

  @Test
  public void ok() {
    Response<Boolean> response = ResponseLib.ok();
    assertStatus(response, HttpStatus.OK);
  }

  @Test
  public void ok_page_mapper() {
    Response<List<Integer>> response = ResponseLib.ok(createPage(), this::parseInts);
    assertStatus(response, HttpStatus.OK);
    assertPageResponse(response.getPage());
    assertContentInteger(response.getData());
  }

  @Test
  public void badRequest_errors_data() {
    Response<Boolean> response = ResponseLib.badRequest(createErrors(), true);
    assertStatus(response, HttpStatus.BAD_REQUEST);
    assertErrors(response.getErrors());
    assertTrue(response.getData());
  }

  @Test
  public void badRequest_errors() {
    Response<Boolean> response = ResponseLib.badRequest(createErrors());
    assertStatus(response, HttpStatus.BAD_REQUEST);
    assertErrors(response.getErrors());
    assertNull(response.getData());
  }

  private List<Integer> parseInts(Page<String> page) {
    return page.stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  private void assertPageResponse(PagingResponse pageResponse) {
    assertEquals(NUMBER, pageResponse.getNumber());
    assertEquals(SIZE, pageResponse.getSize());
    assertEquals(NUMBER_OF_ELEMENTS, pageResponse.getNumberOfElements());
    assertEquals(TOTAL_ELEMENTS, pageResponse.getTotalElements());
    assertEquals(TOTAL_PAGES, pageResponse.getTotalPages());
  }

  private void assertStatus(Response<?> response, HttpStatus status) {
    assertEquals(status.value(), response.getCode().intValue());
    assertEquals(status.name(), response.getStatus());
  }

  private void assertContentString(List<String> content) {
    assertEquals(2, content.size());
    assertEquals(CONTENT_1, content.get(0));
    assertEquals(CONTENT_2, content.get(1));
  }

  private void assertContentInteger(List<Integer> content) {
    assertEquals(2, content.size());
    assertEquals(Integer.parseInt(CONTENT_1), content.get(0).intValue());
    assertEquals(Integer.parseInt(CONTENT_2), content.get(1).intValue());
  }

  private void assertErrors(SetValueMap<String, String> errors) {
    assertEquals(2, errors.size());
    assertEquals(1, errors.get("1").size());
    assertEquals(CollectionLib.toSet("Code1"), errors.get("1"));
    assertEquals(2, errors.get("2").size());
    assertEquals(CollectionLib.toSet("Code2", "Code3"), errors.get("2"));
  }

  private Page<String> createPage() {
    return new PageImpl<>(createContent(), createPageable(), TOTAL_ELEMENTS);
  }

  private List<String> createContent() {
    return Arrays.asList(CONTENT_1, CONTENT_2);
  }

  private Pageable createPageable() {
    return PageRequest.of(NUMBER.intValue(), SIZE.intValue());
  }

  private SetValueMap<String, String> createErrors() {
    return new SetValueHashMap<String, String>()
        .add("1", "Code1")
        .add("2", "Code2")
        .add("2", "Code3");
  }

  private PagingResponse createPageResponse() {
    return PagingResponse.builder()
        .number(NUMBER)
        .size(SIZE)
        .numberOfElements(NUMBER_OF_ELEMENTS)
        .totalElements(TOTAL_ELEMENTS)
        .totalPages(TOTAL_PAGES)
        .build();
  }

}
