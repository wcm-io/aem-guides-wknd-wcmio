package com.adobe.aem.guides.wknd.core.config.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.service.component.annotations.Component;

import com.adobe.aem.guides.wknd.core.config.AppTemplate;
import com.day.cq.wcm.api.Page;

import io.wcm.handler.link.markup.SimpleLinkMarkupBuilder;
import io.wcm.handler.link.spi.LinkHandlerConfig;
import io.wcm.handler.link.spi.LinkMarkupBuilder;
import io.wcm.handler.link.spi.LinkProcessor;
import io.wcm.handler.link.spi.LinkType;
import io.wcm.handler.link.type.ExternalLinkType;
import io.wcm.handler.link.type.InternalLinkType;
import io.wcm.handler.link.type.MediaLinkType;
import io.wcm.siteapi.genericedit.handler.link.SiteApiInternalLinkInheritGenericEditSelectorLinkPreProcessor;
import io.wcm.siteapi.handler.link.SiteApiLinkMarkupBuilder;
import io.wcm.siteapi.handler.link.SiteApiLinkPreProcessor;
import io.wcm.wcm.commons.util.Template;

/**
 * Link handler configuration.
 */
@Component(service = LinkHandlerConfig.class)
public class LinkHandlerConfigImpl extends LinkHandlerConfig {

  private static final List<Class<? extends LinkType>> DEFAULT_LINK_TYPES = List.of(
      InternalLinkType.class,
      ExternalLinkType.class,
      MediaLinkType.class);

  private static final List<Class<? extends LinkProcessor>> PRE_PROCESSORS = List.of(
      SiteApiLinkPreProcessor.class, SiteApiInternalLinkInheritGenericEditSelectorLinkPreProcessor.class);

  private static final List<Class<? extends LinkMarkupBuilder>> LINK_MARKUP_BUILDERS = List.of(
      SiteApiLinkMarkupBuilder.class,
      SimpleLinkMarkupBuilder.class);

  @Override
  @SuppressWarnings("squid:S2384") // returned list is immutable
  public @NotNull List<Class<? extends LinkType>> getLinkTypes() {
    return DEFAULT_LINK_TYPES;
  }

  @Override
  @SuppressWarnings("squid:S2384") // list is immutable
  public @NotNull List<Class<? extends LinkProcessor>> getPreProcessors() {
    return PRE_PROCESSORS;
  }

  @Override
  @SuppressWarnings("squid:S2384") // list is immutable
  public @NotNull List<Class<? extends LinkMarkupBuilder>> getMarkupBuilders() {
    return LINK_MARKUP_BUILDERS;
  }

  @Override
  public boolean isValidLinkTarget(@NotNull Page page) {
    return !Template.is(page, AppTemplate.ADMIN_STRUCTURE_ELEMENT);
  }

  @Override
  public boolean isRedirect(@NotNull Page page) {
    return Template.is(page, AppTemplate.ADMIN_REDIRECT);
  }

  @Override
  public @Nullable String getLinkRootPath(@NotNull Page page, @NotNull String linkTypeId) {
    if (StringUtils.equals(linkTypeId, MediaLinkType.ID)) {
      return MediaHandlerConfigImpl.DAM_ROOT;
    }
    return super.getLinkRootPath(page, linkTypeId);
  }

}
