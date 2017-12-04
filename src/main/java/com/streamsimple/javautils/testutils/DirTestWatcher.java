package com.streamsimple.javautils.testutils;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class DirTestWatcher extends TestWatcher {
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
  protected void starting(Description description) {
    String methodName = description.getMethodName();
    String className = description.getClassName();
    dir = Paths.get(".", "target", className, methodName).toFile();
    dirPath = dir.getAbsolutePath();

    deleteDir();
    dir.mkdirs();
  }

  @Override
  protected void finished(Description description) {
    deleteDirEnd();
  }

  @Override
  protected void failed(Throwable e, Description description) {
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

  public File makeSubDir(String subDirName) {
    File subDir = new File(dir, subDirName);
    subDir.mkdirs();
    return subDir;
  }

  @Deprecated
  public String getDirPath() {
    return dirPath;
  }

  public File getDir() {
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
