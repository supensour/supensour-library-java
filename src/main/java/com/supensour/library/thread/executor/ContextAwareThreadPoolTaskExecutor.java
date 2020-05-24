package com.supensour.library.thread.executor;

import com.supensour.library.libs.ErrorTolerantLib;
import com.supensour.library.model.thread.ThreadContext;
import com.supensour.library.thread.task.ContextAwareCallable;
import com.supensour.library.thread.task.ContextAwareRunnable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@Slf4j
@SuppressWarnings("serial")
public class ContextAwareThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

  @Override
  public Future<?> submit(Runnable task) {
    return super.submit(new ContextAwareRunnable(task, newThreadContext()));
  }

  @Override
  public <T> Future<T> submit(Callable<T> task) {
    return super.submit(new ContextAwareCallable<>(task, newThreadContext()));
  }

  @Override
  public ListenableFuture<?> submitListenable(Runnable task) {
    return super.submitListenable(new ContextAwareRunnable(task, newThreadContext()));
  }

  @Override
  public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
    return super.submitListenable(new ContextAwareCallable<>(task, newThreadContext()));
  }

  protected ThreadContext newThreadContext() {
    return ThreadContext.builder()
        .requestAttributes(ErrorTolerantLib.execute(this::getRequestAttributes, (Consumer<Exception>)
            e -> log.debug("Failed to get current request attributes", e)))
        .mdcContextMap(ErrorTolerantLib.execute(this::getMdcContextMap, (Consumer<Exception>)
            e -> log.debug("Failed to get MDC context map", e)))
        .build();
  }

  protected RequestAttributes getRequestAttributes() {
    return RequestContextHolder.currentRequestAttributes();
  }

  protected Map<String, String> getMdcContextMap() {
    return MDC.getCopyOfContextMap();
  }

}
