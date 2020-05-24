package com.supensour.library.thread.task;

import com.supensour.library.model.thread.ThreadContext;
import org.springframework.lang.NonNull;

import java.util.concurrent.Callable;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
public class ContextAwareCallable<T> extends ContextAwareThreadTask<T> implements Callable<T> {

  public ContextAwareCallable(@NonNull Callable<T> task, ThreadContext threadContext) {
    super(task, threadContext);
  }

  @Override
  public T call() throws Exception {
    return execute();
  }

}
