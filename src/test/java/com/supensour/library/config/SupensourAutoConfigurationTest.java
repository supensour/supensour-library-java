package com.supensour.library.config;

import com.supensour.library.sample.TestApplication;
import com.supensour.library.sample.config.SwaggerCustomConfig;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class SupensourAutoConfigurationTest extends TestCase {

  @SpyBean
  private SwaggerCustomConfig swaggerCustomConfig;

  @After
  public void tearDown() {
    verifyNoMoreInteractions(swaggerCustomConfig);
  }

  @Test
  public void swaggerCustomConfig() {
    verify(swaggerCustomConfig).addGenericModelSubstitutes(any());
    verify(swaggerCustomConfig).addGlobalParameter(any());
    verify(swaggerCustomConfig).addIgnoredParameterTypes(any());
  }

}
