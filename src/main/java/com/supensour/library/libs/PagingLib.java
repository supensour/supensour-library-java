package com.supensour.library.libs;

import com.supensour.library.model.web.PagingRequest;
import com.supensour.library.model.web.PagingResponse;
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

  public static Pageable toPageable(PagingRequest page) {
    Sort sort = Optional.ofNullable(page.getSorts())
        .filter(sorts -> !sorts.isEmpty())
        .map(PagingLib::toOrders)
        .map(Sort::by)
        .orElseGet(Sort::unsorted);
    return PageRequest.of(page.getNumber().intValue(), page.getSize().intValue(), sort);
  }

  public static List<Sort.Order> toOrders(List<PagingRequest.SortingRequest> sortRequests) {
    return sortRequests.stream()
        .map(PagingLib::toOrder)
        .collect(Collectors.toList());
  }

  public static Sort.Order toOrder(PagingRequest.SortingRequest sort) {
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
