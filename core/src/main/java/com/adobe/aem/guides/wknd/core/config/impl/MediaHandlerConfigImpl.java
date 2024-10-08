package com.adobe.aem.guides.wknd.core.config.impl;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;

import io.wcm.handler.media.spi.MediaHandlerConfig;
import io.wcm.handler.media.spi.MediaSource;
import io.wcm.handler.mediasource.dam.DamMediaSource;
import io.wcm.handler.mediasource.inline.InlineMediaSource;
import io.wcm.handler.mediasource.ngdm.NextGenDynamicMediaMediaSource;

/**
 * Media handler configuration.
 */
@Component(service = MediaHandlerConfig.class)
public class MediaHandlerConfigImpl extends MediaHandlerConfig {

  static final String DAM_ROOT = "/content/dam";

  private static final List<Class<? extends MediaSource>> MEDIA_SOURCES = List.of(
      NextGenDynamicMediaMediaSource.class,
      DamMediaSource.class,
      InlineMediaSource.class);

  @Override
  @SuppressWarnings("squid:S2384") // returned list is immutable
  public @NotNull List<Class<? extends MediaSource>> getSources() {
    return MEDIA_SOURCES;
  }

  @Override
  public boolean useAdobeStandardNames() {
    // use standard names for asset references as used by the core components
    return true;
  }

  @Override
  public @NotNull String getDamRootPath(@NotNull Page page) {
    return DAM_ROOT;
  }

  @Override
  public boolean includeAssetWebRenditionsByDefault() {
    return false;
  }

}
