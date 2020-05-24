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
public class ContextAwareCallableTest extends ContextAwareThreadTaskTestHelper {

  @Test
  public void call_valid_success() throws Exception {
    ThreadContext threadContext = ThreadContext.builder()
        .requestAttributes(buildServletRequestAttributes())
        .mdcContextMap(buildMdcContextMap())
        .build();
    ContextAwareCallable<String> callable = new ContextAwareCallable<>(this::getResult, threadContext);
    assertEquals(RESULT, callable.call());
    assertClearedRequestAttributes();
    assertClearedMdcContextMap();
  }

  @Test(expected = IllegalStateException.class)
  public void call_exception_failed() throws Exception {
    ThreadContext threadContext = ThreadContext.builder()
        .requestAttributes(buildServletRequestAttributes())
        .mdcContextMap(buildMdcContextMap())
        .build();
    try {
      ContextAwareCallable<String> callable = new ContextAwareCallable<>(this::throwError, threadContext);
      callable.call();
    } catch (IllegalStateException e) {
      assertEquals(EXCEPTION, e);
      assertClearedRequestAttributes();
      assertClearedMdcContextMap();
      throw e;
    }
  }

}
