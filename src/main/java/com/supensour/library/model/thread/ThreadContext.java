package com.supensour.library.model.thread;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadContext {

  private RequestAttributes requestAttributes;

  private Map<String, String> mdcContextMap;

}
