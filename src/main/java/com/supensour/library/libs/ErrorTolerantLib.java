package com.supensour.library.libs;

import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
public class ErrorTolerantLib {

  private ErrorTolerantLib() {}

  public static void execute(@NonNull Runnable runnable) {
    execute(runnable, null);
  }

  public static void execute(@NonNull Runnable runnable, Consumer<Exception> onError) {
    try {
      runnable.run();
    } catch (Exception e) {
      Optional.ofNullable(onError)
          .ifPresent(consumer -> consumer.accept(e));
    }
  }

  public static <T> T execute(@NonNull Callable<T> callable) {
    return execute(callable, (Function<Exception, T>) null);
  }

  public static <T> T execute(@NonNull Callable<T> callable, Consumer<Exception> onError) {
    Function<Exception, T> onErrorResultProvider = throwable -> {
      Optional.ofNullable(onError)
          .ifPresent(consumer -> consumer.accept(throwable));
      return null;
    };
    return execute(callable, onErrorResultProvider);
  }

  public static <T> T execute(@NonNull Callable<T> callable, Function<Exception, T> onErrorResultProvider) {
    try {
      return callable.call();
    } catch (Exception e) {
      return Optional.ofNullable(onErrorResultProvider)
          .map(provider -> provider.apply(e))
          .orElse(null);
    }
  }

}
