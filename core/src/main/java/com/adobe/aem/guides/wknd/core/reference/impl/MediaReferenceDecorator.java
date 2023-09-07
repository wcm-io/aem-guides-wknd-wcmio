package com.adobe.aem.guides.wknd.core.reference.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.service.component.annotations.Component;

import com.adobe.aem.guides.wknd.core.reference.MediaReference;

import io.wcm.handler.media.Media;
import io.wcm.siteapi.handler.media.MediaDecorator;

/**
 * Decorates media with project-specific {@link MediaReference}.
 */
@Component(service = MediaDecorator.class)
public class MediaReferenceDecorator implements MediaDecorator<MediaReference> {

  @Override
  public @Nullable MediaReference apply(@NotNull Media media) {
    return new MediaReference(media);
  }

}
