package com.supensour.library.thread.executor;

import com.supensour.library.sample.thread.executor.MockedErrorContextAwareThreadPoolTaskExecutor;
import com.supensour.library.thread.task.ContextAwareThreadTaskTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.MDC;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ContextAwareThreadPoolTaskExecutorTest extends ContextAwareThreadTaskTestHelper {

  private ContextAwareThreadPoolTaskExecutor executor;

  private MockedErrorContextAwareThreadPoolTaskExecutor errorExecutor;

  @Before
  public void setUp() {
    executor = new ContextAwareThreadPoolTaskExecutor();
    executor.afterPropertiesSet();

    errorExecutor = new MockedErrorContextAwareThreadPoolTaskExecutor();
    errorExecutor.afterPropertiesSet();

    RequestContextHolder.setRequestAttributes(buildServletRequestAttributes());
    MDC.setContextMap(buildMdcContextMap());
  }

  @After
  public void tearDown() {
    RequestContextHolder.resetRequestAttributes();
    MDC.clear();
  }

  @Test
  public void submitRunnable_valid_success() throws Exception {
    Future<?> futureResult = executor.submit((Runnable) this::getResult);
    assertNull(futureResult.get());
  }

  @Test
  public void submitCallable_valid_success() throws Exception {
    Future<String> futureResult = executor.submit(this::getResult);
    assertEquals(RESULT, futureResult.get());
  }

  @Test
  public void submitCallable_failedToSetContext_success() throws Exception {
    Future<String> futureResult = errorExecutor.submit(this::getResultWithEmptyContext);
    assertEquals(RESULT, futureResult.get());
  }

  @Test
  public void submitListenableRunnable_valid_success() throws Exception {
    ListenableFuture<?> listenableFutureResult = executor.submitListenable((Runnable) this::getResult);
    assertNull(listenableFutureResult.get());
  }

  @Test
  public void submitListenableCallable_valid_success() throws Exception {
    ListenableFuture<?> listenableFutureResult = executor.submitListenable(this::getResult);
    assertEquals(RESULT, listenableFutureResult.get());
  }

}
