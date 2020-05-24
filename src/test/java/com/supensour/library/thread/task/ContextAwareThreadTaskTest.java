package com.supensour.library.thread.task;

import com.supensour.library.model.thread.ThreadContext;
import com.supensour.library.sample.thread.task.MockedErrorContextAwareThreadTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ContextAwareThreadTaskTest extends ContextAwareThreadTaskTestHelper {

  @Test
  public void execute_valid_success() throws Exception {
    ThreadContext threadContext = ThreadContext.builder()
        .requestAttributes(buildServletRequestAttributes())
        .mdcContextMap(buildMdcContextMap())
        .build();
    ContextAwareThreadTask<String> callable = new ContextAwareThreadTask<>(this::getResult, threadContext);
    assertEquals(RESULT, callable.execute());
    assertClearedRequestAttributes();
    assertClearedMdcContextMap();
  }

  @Test(expected = IllegalStateException.class)
  public void execute_exception_failed() throws Exception {
    ThreadContext threadContext = ThreadContext.builder()
        .requestAttributes(buildServletRequestAttributes())
        .mdcContextMap(buildMdcContextMap())
        .build();
    try {
      ContextAwareThreadTask<String> callable = new ContextAwareThreadTask<>(this::throwError, threadContext);
      callable.execute();
    } catch (IllegalStateException e) {
      assertEquals(EXCEPTION, e);
      assertClearedRequestAttributes();
      assertClearedMdcContextMap();
      throw e;
    }
  }

  @Test
  public void execute_failedToSetAndClearContext_success() throws Exception {
    ThreadContext threadContext = ThreadContext.builder()
        .requestAttributes(buildServletRequestAttributes())
        .mdcContextMap(buildMdcContextMap())
        .build();
    ContextAwareThreadTask<String> callable =
        new MockedErrorContextAwareThreadTask<>(this::getResultWithEmptyContext, threadContext);
    assertEquals(RESULT, callable.execute());
    assertClearedRequestAttributes();
    assertClearedMdcContextMap();
  }

}
