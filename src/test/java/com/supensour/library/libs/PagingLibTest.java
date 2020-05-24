package com.supensour.library.libs;

import com.supensour.library.model.web.PagingRequest;
import com.supensour.library.model.web.PagingResponse;
import com.supensour.library.model.web.SortingRequest;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class PagingLibTest {

  @Test
  public void toPageable() {
    PagingRequest pagingRequest = PagingRequest.builder()
        .number(0L)
        .size(10L)
        .build();
    Pageable pageable = PagingLib.toPageable(pagingRequest);
    List<Sort.Order> orders = pageable.getSort().get().collect(Collectors.toList());
    assertEquals(0, pageable.getPageNumber());
    assertEquals(10, pageable.getPageSize());
    assertEquals(0, orders.size());
  }

  @Test
  public void toPageable_sort() {
    SortingRequest sortingRequest = SortingRequest.builder()
        .field("field-1")
        .ignoreCase(true)
        .build();
    PagingRequest pagingRequest = PagingRequest.builder()
        .number(0L)
        .size(10L)
        .build();
    Pageable pageable = PagingLib.toPageable(pagingRequest, sortingRequest);
    List<Sort.Order> orders = pageable.getSort().get().collect(Collectors.toList());
    assertEquals(0, pageable.getPageNumber());
    assertEquals(10, pageable.getPageSize());
    assertEquals(1, orders.size());
    assertEquals("field-1", orders.get(0).getProperty());
    assertEquals(Sort.Direction.ASC, orders.get(0).getDirection());
    assertTrue(orders.get(0).isIgnoreCase());
    assertEquals(Sort.NullHandling.NATIVE, orders.get(0).getNullHandling());
  }

  @Test
  public void toPageable_sorts() {
    List<SortingRequest> sortingRequests = new ArrayList<>();
    sortingRequests.add(SortingRequest.builder().field("field-1").build());
    sortingRequests.add(SortingRequest.builder().field("field-2").ignoreCase(true).build());
    PagingRequest pagingRequest = PagingRequest.builder()
        .number(0L)
        .size(10L)
        .build();
    Pageable pageable = PagingLib.toPageable(pagingRequest, sortingRequests);
    List<Sort.Order> orders = pageable.getSort().get().collect(Collectors.toList());
    assertEquals(0, pageable.getPageNumber());
    assertEquals(10, pageable.getPageSize());
    assertEquals(2, orders.size());
    assertEquals("field-1", orders.get(0).getProperty());
    assertEquals("field-2", orders.get(1).getProperty());
    assertEquals(Sort.Direction.ASC, orders.get(0).getDirection());
    assertEquals(Sort.Direction.ASC, orders.get(1).getDirection());
    assertFalse(orders.get(0).isIgnoreCase());
    assertTrue(orders.get(1).isIgnoreCase());
    assertEquals(Sort.NullHandling.NATIVE, orders.get(0).getNullHandling());
    assertEquals(Sort.NullHandling.NATIVE, orders.get(1).getNullHandling());
  }

  @Test
  public void toPagingResponse() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<String> page = new PageImpl<>(new ArrayList<>(), pageable, 100);
    PagingResponse pageResponse = PagingLib.toPagingResponse(page);
    assertEquals(0, pageResponse.getNumber().intValue());
    assertEquals(10, pageResponse.getSize().intValue());
    assertEquals(0, pageResponse.getNumberOfElements().intValue());
    assertEquals(10, pageResponse.getTotalPages().intValue());
    assertEquals(100, pageResponse.getTotalElements().intValue());
  }

  @Test
  public void toPagingResponse_params() {
    PagingRequest pagingRequest = PagingRequest.builder()
        .number(0L)
        .size(10L)
        .build();
    PagingResponse pageResponse = PagingLib.toPagingResponse(pagingRequest, 0L, 10L, 100L);
    assertEquals(0, pageResponse.getNumber().intValue());
    assertEquals(10, pageResponse.getSize().intValue());
    assertEquals(0, pageResponse.getNumberOfElements().intValue());
    assertEquals(10, pageResponse.getTotalPages().intValue());
    assertEquals(100, pageResponse.getTotalElements().intValue());
  }

}
