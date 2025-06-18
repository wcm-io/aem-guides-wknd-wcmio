package com.adobe.aem.guides.wknd.core.reference;

import org.jetbrains.annotations.NotNull;

import io.wcm.handler.media.Rendition;
import io.wcm.handler.media.UriTemplateType;

/**
 * Represents a media rendition in Site API.
 */
public class MediaRendition {

  private final Rendition rendition;

  MediaRendition(Rendition rendition) {
    this.rendition = rendition;
  }

  /**
   * @return Rendition URL
   */
  public @NotNull String getUrl() {
    return this.rendition.getUrl();
  }

  /**
   * @return URI template
   */
  public @NotNull String getUriTemplate() {
    return this.rendition.getUriTemplate(UriTemplateType.SCALE_WIDTH).getUriTemplate();
  }

  /**
   * @return Max. width
   */
  public long getWidth() {
    return this.rendition.getWidth();
  }

  /**
   * @return Max height
   */
  public long getHeight() {
    return this.rendition.getHeight();
  }

  /**
   * @return Ratio
   */
  public double getRatio() {
    return this.rendition.getRatio();
  }

}
