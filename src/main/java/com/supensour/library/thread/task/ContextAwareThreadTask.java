package com.supensour.library.thread.task;

import com.supensour.library.libs.ErrorTolerantLib;
import com.supensour.library.model.thread.ThreadContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@Slf4j
public class ContextAwareThreadTask<T> {

  @NonNull
  private final Callable<T> task;

  private final ThreadContext threadContext;

  public ContextAwareThreadTask(@NonNull Callable<T> task, ThreadContext threadContext) {
    this.task = task;
    this.threadContext = threadContext;
  }

  public final T execute() throws Exception {
    initializeContext();
    try {
      return task.call();
    } finally {
      clearContext();
    }
  }

  protected void initializeContext() {
    ErrorTolerantLib.execute(this::setRequestAttributes,
        e -> log.debug("Failed to set request attributes", e));
    ErrorTolerantLib.execute(this::setMdcContextMap,
        e -> log.debug("Failed to set MDC context map", e));
  }

  protected void clearContext() {
    ErrorTolerantLib.execute(this::clearRequestAttributes,
        e -> log.debug("Failed to clear request attributes", e));
    ErrorTolerantLib.execute(this::clearMdcContextMap,
        e -> log.debug("Failed to clear MDC context map", e));
  }

  protected void setRequestAttributes() {
    RequestAttributes requestAttributes = Optional.ofNullable(threadContext)
        .map(ThreadContext::getRequestAttributes)
        .orElse(null);
    RequestContextHolder.setRequestAttributes(requestAttributes);
  }

  protected void clearRequestAttributes() {
    RequestContextHolder.resetRequestAttributes();
  }

  protected void setMdcContextMap() {
    Map<String, String> mdcContextMap = Optional.ofNullable(threadContext)
        .map(ThreadContext::getMdcContextMap)
        .orElseGet(HashMap::new);
    MDC.setContextMap(mdcContextMap);
  }

  protected void clearMdcContextMap() {
    MDC.clear();
  }

}
