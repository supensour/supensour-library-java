package com.supensour.library.sample.thread.task;

import com.supensour.library.model.thread.ThreadContext;
import com.supensour.library.thread.task.ContextAwareThreadTask;

import java.util.concurrent.Callable;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
public class MockedErrorContextAwareThreadTask<T> extends ContextAwareThreadTask<T> {

  public MockedErrorContextAwareThreadTask(Callable<T> task, ThreadContext threadContext) {
    super(task, threadContext);
  }

  @Override
  protected void setRequestAttributes() {
    throw new IllegalStateException();
  }

  @Override
  protected void clearRequestAttributes() {
    throw new IllegalStateException();
  }

  @Override
  protected void setMdcContextMap() {
    throw new IllegalStateException();
  }

  @Override
  protected void clearMdcContextMap() {
    throw new IllegalStateException();
  }

}
