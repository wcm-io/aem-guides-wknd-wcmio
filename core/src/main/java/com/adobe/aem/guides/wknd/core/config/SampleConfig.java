package com.adobe.aem.guides.wknd.core.config;

import static io.wcm.caconfig.editor.EditorProperties.PROPERTY_PATHBROWSER_ROOT_PATH;
import static io.wcm.caconfig.editor.EditorProperties.PROPERTY_PATHBROWSER_ROOT_PATH_CONTEXT;
import static io.wcm.caconfig.editor.EditorProperties.PROPERTY_WIDGET_TYPE;
import static io.wcm.caconfig.editor.EditorProperties.WIDGET_TYPE_PATHBROWSER;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

/**
 * Context-Aware Configuration example
 */
@Configuration(label = "wknd Sample Configuration",
    description = "This is a sample configuration.")
public @interface SampleConfig {

  /**
   * @return String parameter
   */
  @Property(label = "String Param", description = "This is a string parameter.", order = 10)
  String stringParam();

  /**
   * @return Integer parameter
   */
  @Property(label = "Integer Param", order = 12)
  int intParam();

  /**
   * @return Boolean parameter
   */
  @Property(label = "Boolean Param", order = 13)
  boolean boolParam();

  /**
   * @return Path parameter
   */
  @Property(label = "DAM Path", description = "Browse DAM assets.", order = 20, property = {
      PROPERTY_WIDGET_TYPE + "=" + WIDGET_TYPE_PATHBROWSER,
      PROPERTY_PATHBROWSER_ROOT_PATH + "=/content/dam"
  })
  String damPath();

  /**
   * @return Path parameter
   */
  @Property(label = "Content Path", description = "Browse pages in the current configuration context content path.", order = 30,
      property = {
          PROPERTY_WIDGET_TYPE + "=" + WIDGET_TYPE_PATHBROWSER,
          PROPERTY_PATHBROWSER_ROOT_PATH_CONTEXT + "=true"
      })
  String contentPath();

}
