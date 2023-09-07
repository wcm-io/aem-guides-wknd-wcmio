package com.adobe.aem.guides.wknd.core.reference.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.osgi.service.component.annotations.Component;

import com.adobe.cq.wcm.core.components.commons.link.Link;

import io.wcm.siteapi.handler.link.LinkDecorator;
import io.wcm.wcm.core.components.commons.link.LinkWrapper;

/**
 * Decorates wcm.io link with Core Components {@link Link}.
 * Normally it's better to create your won LinkReference object, but in this case we want to stay
 * as close as possible to the Core Components itself.
 */
@SuppressWarnings("java:S3740") // unable to provide generic for Core Component Link here
@Component(service = LinkDecorator.class)
public class LinkReferenceDecorator implements LinkDecorator<Link> {

  @Override
  public @Nullable Link apply(@NotNull io.wcm.handler.link.Link link) {
    link.getAnchorAttributes();
    return new LinkWrapper(link);
  }

}
