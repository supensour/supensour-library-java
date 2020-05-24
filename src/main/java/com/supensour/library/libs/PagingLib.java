package com.supensour.library.libs;

import com.supensour.library.model.web.PagingRequest;
import com.supensour.library.model.web.PagingResponse;
import com.supensour.library.model.web.SortingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class PagingLib {

  private PagingLib () {}

  public static Pageable toPageable(PagingRequest page) {
    return PageRequest.of(page.getNumber().intValue(), page.getSize().intValue());
  }

  /**
    @since 1.2.0
   */
  public static Pageable toPageable(PagingRequest page, SortingRequest sortRequest) {
    Sort sort = Optional.ofNullable(sortRequest)
        .map(PagingLib::toOrder)
        .map(Sort::by)
        .orElseGet(Sort::unsorted);
    return PageRequest.of(page.getNumber().intValue(), page.getSize().intValue(), sort);
  }

  /**
   * @since 1.2.0
   */
  public static Pageable toPageable(PagingRequest page, List<SortingRequest> sorts) {
    Sort sort = Optional.ofNullable(sorts)
        .filter(CollectionLib::isNotEmpty)
        .map(PagingLib::toOrders)
        .map(Sort::by)
        .orElseGet(Sort::unsorted);
    return PageRequest.of(page.getNumber().intValue(), page.getSize().intValue(), sort);
  }

  public static List<Sort.Order> toOrders(List<SortingRequest> sortRequests) {
    return sortRequests.stream()
        .map(PagingLib::toOrder)
        .collect(Collectors.toList());
  }

  public static Sort.Order toOrder(SortingRequest sort) {
    Sort.Order order = new Sort.Order(sort.getDirection(), sort.getField(), sort.getNullHandling());
    if(Boolean.TRUE.equals(sort.getIgnoreCase())) {
      order = order.ignoreCase();
    }
    return order;
  }

  public static PagingResponse toPagingResponse(Page<?> page) {
    return PagingResponse.builder()
        .number((long) page.getNumber())
        .size((long) page.getSize())
        .numberOfElements((long) page.getNumberOfElements())
        .totalPages((long) page.getTotalPages())
        .totalElements(page.getTotalElements())
        .build();
  }

  public static PagingResponse toPagingResponse(PagingRequest pagingRequest, Long numberOfElements, Long totalPages, Long totalElements) {
    return PagingResponse.builder()
        .number(pagingRequest.getNumber())
        .size(pagingRequest.getSize())
        .numberOfElements(numberOfElements)
        .totalPages(totalPages)
        .totalElements(totalElements)
        .build();
  }

}
