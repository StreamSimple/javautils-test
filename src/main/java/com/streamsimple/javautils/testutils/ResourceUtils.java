package com.streamsimple.javautils.testutils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ResourceUtils
{
  private static final Path ROOT_PATH = Paths.get("/");

  private ResourceUtils()
  {
  }

  public static File getResourceAsFile(final Path resourcePath)
  {
    if (resourcePath.isAbsolute()) {
      final String message = String.format("The given path [%s] is absolute, paths must be relative to " +
          "where resources are stored.", resourcePath);
    }

    final String resourcePathString = ROOT_PATH
        .resolve(resourcePath)
        .toString();

    try {
      final URI resourceURI = ResourceUtils.class
          .getResource(resourcePathString)
          .toURI();
      return new File(resourceURI);
    } catch (URISyntaxException e) {
      // This should never happen
      throw new RuntimeException(e);
    }
  }
}
