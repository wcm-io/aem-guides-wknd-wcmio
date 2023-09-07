package com.adobe.aem.guides.wknd.core.testcontext;

import static com.adobe.cq.wcm.core.components.testing.mock.ContextPlugins.CORE_COMPONENTS;
import static io.wcm.testing.mock.wcmio.caconfig.ContextPlugins.WCMIO_CACONFIG;
import static io.wcm.testing.mock.wcmio.handler.ContextPlugins.WCMIO_HANDLER;
import static io.wcm.testing.mock.wcmio.siteapi.genericedit.ContextPlugins.WCMIO_SITEAPI_GENERICEDIT;
import static io.wcm.testing.mock.wcmio.siteapi.genericedit.handler.ContextPlugins.WCMIO_SITEAPI_GENERICEDIT_HANDLER;
import static io.wcm.testing.mock.wcmio.siteapi.handler.ContextPlugins.WCMIO_SITEAPI_HANDLER;
import static io.wcm.testing.mock.wcmio.siteapi.processor.ContextPlugins.WCMIO_SITEAPI_PROCESSOR;
import static io.wcm.testing.mock.wcmio.sling.ContextPlugins.WCMIO_SLING;
import static io.wcm.testing.mock.wcmio.wcm.ContextPlugins.WCMIO_WCM;
import static org.apache.sling.testing.mock.caconfig.ContextPlugins.CACONFIG;

import java.io.IOException;

import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.jetbrains.annotations.NotNull;

import com.adobe.aem.guides.wknd.core.config.impl.LinkHandlerConfigImpl;
import com.adobe.aem.guides.wknd.core.config.impl.MediaFormatProviderImpl;
import com.adobe.aem.guides.wknd.core.config.impl.MediaHandlerConfigImpl;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextBuilder;
import io.wcm.testing.mock.aem.junit5.AemContextCallback;
import io.wcm.testing.mock.wcmio.caconfig.MockCAConfig;

/**
 * Sets up {@link AemContext} for unit tests in this application.
 */
public final class AppAemContext {

  private AppAemContext() {
    // static methods only
  }

  /**
   * @return {@link AemContext}
   */
  public static AemContext newAemContext() {
    return newAemContextBuilder().build();
  }

  /**
   * @return {@link AemContextBuilder}
   */
  public static AemContextBuilder newAemContextBuilder() {
    return newAemContextBuilder(ResourceResolverType.RESOURCERESOLVER_MOCK);
  }

  /**
   * @return {@link AemContextBuilder}
   */
  public static AemContextBuilder newAemContextBuilder(@NotNull ResourceResolverType resourceResolverType) {
    return new AemContextBuilder(resourceResolverType)
        .plugin(CACONFIG, CORE_COMPONENTS, WCMIO_SLING, WCMIO_WCM, WCMIO_SITEAPI_PROCESSOR, WCMIO_CACONFIG, WCMIO_HANDLER,
            WCMIO_SITEAPI_HANDLER, WCMIO_SITEAPI_GENERICEDIT, WCMIO_SITEAPI_GENERICEDIT_HANDLER)
        .afterSetUp(SETUP_CALLBACK);
  }

  /**
   * Custom set up rules required in all unit tests.
   */
  private static final AemContextCallback SETUP_CALLBACK = new AemContextCallback() {

    @Override
    public void execute(@NotNull AemContext context) throws PersistenceException, IOException {

      // context path strategy
      MockCAConfig.contextPathStrategyAbsoluteParent(context, 1, 2, 3);

      // setup handler
      context.registerInjectActivateService(LinkHandlerConfigImpl.class);
      context.registerInjectActivateService(MediaHandlerConfigImpl.class);
      context.registerInjectActivateService(MediaFormatProviderImpl.class);

    }
  };

}
