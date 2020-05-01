package com.supensour.library.model.web;

import com.supensour.library.libs.CollectionLib;
import com.supensour.library.model.web.PagingRequest.SortingRequest;
import org.junit.Test;
import org.springframework.data.domain.Sort;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class PagingRequestTest {

  @Test
  public void data() {
    SortingRequest sortingRequest = new SortingRequest();
    sortingRequest.setDirection(Sort.Direction.ASC);
    sortingRequest.setField("field");
    sortingRequest.setIgnoreCase(Boolean.TRUE);
    sortingRequest.setNullHandling(Sort.NullHandling.NATIVE);

    PagingRequest pagingRequest = new PagingRequest();
    pagingRequest.setNumber(0L);
    pagingRequest.setSize(10L);
    pagingRequest.setSorts(CollectionLib.toList(sortingRequest));

    assertEquals(0L, pagingRequest.getNumber().longValue());
    assertEquals(10L, pagingRequest.getSize().longValue());
    assertEquals(Sort.Direction.ASC, pagingRequest.getSorts().get(0).getDirection());
    assertEquals("field", pagingRequest.getSorts().get(0).getField());
    assertTrue(pagingRequest.getSorts().get(0).getIgnoreCase());
    assertEquals(Sort.NullHandling.NATIVE, pagingRequest.getSorts().get(0).getNullHandling());
  }

  @Test
  public void builder() {
    SortingRequest sortingRequest = SortingRequest.builder()
        .direction(Sort.Direction.ASC)
        .field("field")
        .ignoreCase(Boolean.TRUE)
        .nullHandling(Sort.NullHandling.NATIVE)
        .build();

    PagingRequest pagingRequest = PagingRequest.builder()
        .number(0L)
        .size(10L)
        .sorts(CollectionLib.toList(sortingRequest))
        .build();

    assertEquals(0L, pagingRequest.getNumber().longValue());
    assertEquals(10L, pagingRequest.getSize().longValue());
    assertEquals(Sort.Direction.ASC, pagingRequest.getSorts().get(0).getDirection());
    assertEquals("field", pagingRequest.getSorts().get(0).getField());
    assertTrue(pagingRequest.getSorts().get(0).getIgnoreCase());
    assertEquals(Sort.NullHandling.NATIVE, pagingRequest.getSorts().get(0).getNullHandling());
  }

}
