/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
