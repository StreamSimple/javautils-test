package com.simplifi.it.javautils.testutils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

public final class ResourceUtils
{
  private ResourceUtils()
  {
  }

  public static File getResourceAsFile(Path resourcePath)
  {
    try {
      final URI resourceURI = ResourceUtils.class
          .getResource(resourcePath.toString())
          .toURI();
      return new File(resourceURI);
    } catch (URISyntaxException e) {
      // This should never happen
      throw new RuntimeException(e);
    }
  }
}
