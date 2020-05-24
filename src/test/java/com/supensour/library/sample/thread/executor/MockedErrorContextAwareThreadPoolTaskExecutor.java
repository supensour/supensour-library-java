package com.supensour.library.sample.thread.executor;

import com.supensour.library.thread.executor.ContextAwareThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
@SuppressWarnings("serial")
public class MockedErrorContextAwareThreadPoolTaskExecutor extends ContextAwareThreadPoolTaskExecutor {

  @Override
  protected RequestAttributes getRequestAttributes() {
    throw new IllegalStateException();
  }

  @Override
  protected Map<String, String> getMdcContextMap() {
    throw new IllegalStateException();
  }

}
