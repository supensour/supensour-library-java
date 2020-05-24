package com.supensour.library.thread.task;

import com.supensour.library.model.thread.ThreadContext;
import org.springframework.lang.NonNull;

import java.util.concurrent.Callable;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
public class ContextAwareRunnable extends ContextAwareThreadTask<Void> implements Runnable {

  public ContextAwareRunnable(@NonNull Runnable task, ThreadContext threadContext) {
    super(toCallable(task), threadContext);
  }

  @Override
  public void run() {
    try {
      execute();
    } catch (Exception e) {
      throw new IllegalStateException("Task execution throws exception", e);
    }
  }

  private static Callable<Void> toCallable(Runnable runnable) {
    return () -> {
      runnable.run();
      return null;
    };
  }

}
