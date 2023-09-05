package com.adobe.aem.guides.wknd.core.config.impl;

import org.osgi.service.component.annotations.Component;

import com.adobe.aem.guides.wknd.core.config.MediaFormats;

import io.wcm.handler.media.spi.MediaFormatProvider;

/**
 * Media format provider.
 */
@Component(service = MediaFormatProvider.class)
public class MediaFormatProviderImpl extends MediaFormatProvider {

  /**
   * Constructor
   */
  public MediaFormatProviderImpl() {
    super(MediaFormats.class);
  }

}
