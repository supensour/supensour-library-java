package com.supensour.library.thread.task;

import org.mockito.Mock;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.2.0
 */
public class ContextAwareThreadTaskTestHelper {

  protected static final String RESULT = "result";
  protected static final String SESSION_ID = "sessionId";
  protected static final String SESSION_ID_KEY = "sessionIdKey";

  protected static final IllegalStateException EXCEPTION = new IllegalStateException();

  @Mock
  protected HttpServletRequest servletRequest;

  @Mock
  protected HttpServletResponse servletResponse;

  protected RequestAttributes buildServletRequestAttributes() {
    return new ServletRequestAttributes(servletRequest, servletResponse);
  }

  protected Map<String, String> buildMdcContextMap() {
    Map<String, String> context = new HashMap<>();
    context.put(SESSION_ID_KEY, SESSION_ID);
    return context;
  }

  protected String getResult() {
    assertInitializedRequestAttributes();
    assertInitializedMdcContextMap();
    return RESULT;
  }

  protected String getResultWithEmptyContext() {
    assertClearedRequestAttributes();
    assertClearedMdcContextMap();
    return RESULT;
  }

  protected String throwError() {
    assertInitializedRequestAttributes();
    assertInitializedMdcContextMap();
    throw EXCEPTION;
  }

  protected void assertInitializedRequestAttributes() {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes();
    assertEquals(servletRequest, requestAttributes.getRequest());
    assertEquals(servletResponse, requestAttributes.getResponse());
  }

  protected void assertInitializedMdcContextMap() {
    assertEquals(1, MDC.getCopyOfContextMap().size());
    assertEquals(SESSION_ID, MDC.get(SESSION_ID_KEY));
  }

  protected void assertClearedRequestAttributes() {
    boolean isEmptyRequestAttributes = false;
    try {
      RequestContextHolder.currentRequestAttributes();
    } catch (IllegalStateException e) {
      isEmptyRequestAttributes = true;
    } finally {
      assertTrue(isEmptyRequestAttributes);
    }
  }

  protected void assertClearedMdcContextMap() {
    assertTrue(CollectionUtils.isEmpty(MDC.getCopyOfContextMap()));
  }

}
