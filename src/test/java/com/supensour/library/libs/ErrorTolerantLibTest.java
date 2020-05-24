package com.supensour.library.libs;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ErrorTolerantLibTest {

  private static final String RESULT_STR = "result";
  private static final String ALTERNATIVE_RESULT_STR = "alternativeResult";

  @Mock
  private Runnable runnable;

  @Mock
  private Callable<Object> callable;

  @Mock
  private Consumer<Exception> errorConsumer;

  @Mock
  private Function<Exception, Object> errorMapper;

  @After
  public void tearDown() {
    verifyNoMoreInteractions(runnable);
    verifyNoMoreInteractions(callable);
    verifyNoMoreInteractions(errorConsumer);
    verifyNoMoreInteractions(errorMapper);
  }

  @Test
  public void executeRunnable_valid_success() {
    ErrorTolerantLib.execute(runnable);
    verify(runnable, times(1)).run();
  }

  @Test
  public void executeRunnable_invalid_success() {
    doThrow(IllegalStateException.class).when(runnable).run();

    ErrorTolerantLib.execute(runnable);
    verify(runnable, times(1)).run();
  }

  @Test
  public void executeRunnableWithOnerror_valid_success() {
    ErrorTolerantLib.execute(runnable, errorConsumer);
    verify(runnable, times(1)).run();
    verify(errorConsumer, times(0)).accept(any(Exception.class));
  }

  @Test
  public void executeRunnableWithOnerror_invalid_success() {
    doThrow(IllegalStateException.class).when(runnable).run();

    ErrorTolerantLib.execute(runnable, errorConsumer);
    verify(runnable, times(1)).run();
    verify(errorConsumer, times(1)).accept(any(IllegalStateException.class));
  }

  @Test
  public void executeCallable_valid_success() throws Exception {
    when(callable.call()).thenReturn(RESULT_STR);

    assertEquals(RESULT_STR, ErrorTolerantLib.execute(callable));
    verify(callable, times(1)).call();
  }

  @Test
  public void executeCallable_invalid_success() throws Exception {
    when(callable.call()).thenThrow(IllegalStateException.class);

    assertNull(ErrorTolerantLib.execute(callable));
    verify(callable, times(1)).call();
  }

  @Test
  public void executeCallableWithConsumer_valid_success() throws Exception {
    when(callable.call()).thenReturn(RESULT_STR);

    assertEquals(RESULT_STR, ErrorTolerantLib.execute(callable, errorConsumer));
    verify(callable, times(1)).call();
    verify(errorConsumer, times(0)).accept(any(Exception.class));
  }

  @Test
  public void executeCallableWithConsumer_invalid_success() throws Exception {
    when(callable.call()).thenThrow(IllegalStateException.class);

    assertNull(ErrorTolerantLib.execute(callable, errorConsumer));
    verify(callable, times(1)).call();
    verify(errorConsumer, times(1)).accept(any(IllegalStateException.class));
  }

  @Test
  public void executeCallableWithOnErrorResultProvider_valid_success() throws Exception {
    when(callable.call()).thenReturn(RESULT_STR);

    assertEquals(RESULT_STR, ErrorTolerantLib.execute(callable, errorMapper));
    verify(callable, times(1)).call();
    verify(errorMapper, times(0)).apply(any(Exception.class));
  }

  @Test
  public void executeCallableWithOnErrorResultProvider_invalid_success() throws Exception {
    when(callable.call()).thenThrow(IllegalStateException.class);
    when(errorMapper.apply(any(IllegalStateException.class))).thenReturn(ALTERNATIVE_RESULT_STR);

    assertEquals(ALTERNATIVE_RESULT_STR, ErrorTolerantLib.execute(callable, errorMapper));
    verify(callable, times(1)).call();
    verify(errorMapper, times(1)).apply(any(IllegalStateException.class));
  }

}
