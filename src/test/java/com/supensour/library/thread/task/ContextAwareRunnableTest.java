package com.supensour.library.thread.task;

import com.supensour.library.model.thread.ThreadContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ContextAwareRunnableTest extends ContextAwareThreadTaskTestHelper {

  @Test
  public void run_valid_success() {
    ThreadContext threadContext = ThreadContext.builder()
        .requestAttributes(buildServletRequestAttributes())
        .mdcContextMap(buildMdcContextMap())
        .build();
    ContextAwareRunnable runnable = new ContextAwareRunnable(this::getResult, threadContext);
    runnable.run();
    assertClearedRequestAttributes();
    assertClearedMdcContextMap();
  }

  @Test(expected = IllegalStateException.class)
  public void run_exception_failed() {
    ThreadContext threadContext = ThreadContext.builder()
        .requestAttributes(buildServletRequestAttributes())
        .mdcContextMap(buildMdcContextMap())
        .build();
    try {
      ContextAwareRunnable runnable = new ContextAwareRunnable(this::throwError, threadContext);
      runnable.run();
    } catch (IllegalStateException e) {
      assertEquals(IllegalStateException.class, e.getClass());
      assertClearedRequestAttributes();
      assertClearedMdcContextMap();
      throw e;
    }
  }

}
