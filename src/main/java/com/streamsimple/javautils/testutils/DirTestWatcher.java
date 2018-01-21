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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.streamsimple.commons.io.FileUtils;

public class DirTestWatcher extends TestWatcher
{
  private String dirPath;
  private File dir;
  private boolean deleteAtEnd = false;

  @Deprecated
  public DirTestWatcher()
  {
  }

  protected DirTestWatcher(final boolean deleteAtEnd)
  {
    this.deleteAtEnd = deleteAtEnd;
  }

  @Override
  protected void starting(Description description)
  {
    String methodName = description.getMethodName();
    String className = description.getClassName();
    dir = Paths.get(".", "target", className, methodName).toFile();
    dirPath = dir.getAbsolutePath();

    deleteDir();
    dir.mkdirs();
  }

  @Override
  protected void finished(Description description)
  {
    deleteDirEnd();
  }

  @Override
  protected void failed(Throwable e, Description description)
  {
    deleteDirEnd();
  }

  private void deleteDir()
  {
    try {
      FileUtils.deleteDirectory(dir);
    } catch (IOException ex) {
      // Just swallow
    }
  }

  private void deleteDirEnd()
  {
    if (deleteAtEnd) {
      deleteDir();
    }
  }

  @Deprecated
  public File makeSubDir(String subDirName)
  {
    final File subDir = new File(dir, subDirName);
    subDir.mkdirs();
    return subDir;
  }

  public File makeSubDir(Path subDirPath)
  {
    final File subDir = dir.toPath().resolve(subDirPath).toFile();
    subDir.mkdirs();
    return subDir;
  }

  @Deprecated
  public String getDirPath()
  {
    return dirPath;
  }

  public File getDir()
  {
    return dir;
  }

  public static class Builder
  {
    private boolean deleteAtEnd = true;

    public Builder()
    {
    }

    public Builder setDeleteAtEnd(final boolean deleteAtEnd)
    {
      this.deleteAtEnd = deleteAtEnd;
      return this;
    }

    public DirTestWatcher build()
    {
      return new DirTestWatcher(deleteAtEnd);
    }
  }
}
