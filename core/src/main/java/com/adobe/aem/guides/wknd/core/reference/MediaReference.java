package com.adobe.aem.guides.wknd.core.reference;

import java.util.Collection;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import io.wcm.handler.media.Media;

/**
 * Represents a media reference in Site API.
 */
public class MediaReference {

  private final Media media;
  private final Collection<MediaRendition> renditions;

  /**
   * @param media Valid media
   */
  public MediaReference(Media media) {
    this.media = media;
    this.renditions = media.getRenditions().stream()
        .map(MediaRendition::new)
        .collect(Collectors.toList());
  }

  /**
   * @return Asset path
   */
  public @NotNull String getPath() {
    return media.getAsset().getPath();
  }

  /**
   * @return Renditions
   */
  public Collection<MediaRendition> getRenditions() {
    return renditions;
  }

}
