package com.simplifi.it.javautils.testutils;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class DirTestWatcher extends TestWatcher {
  private String dirPath;
  private File dir;
  
  @Override
  protected void starting(Description description) {
    String methodName = description.getMethodName();
    String className = description.getClassName();
    dir = Paths.get(".", "target", className, methodName).toFile();
    dirPath = dir.getAbsolutePath();
    dir.mkdirs();
  }

  @Override
  protected void finished(Description description) {
    try {
      FileUtils.deleteDirectory(getDir());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void failed(Throwable e, Description description) {
    try {
      FileUtils.deleteDirectory(getDir());
    } catch (IOException ex) {
      // Just swallow
    }
  }

  public File makeSubDir(String subDirName) {
    File subDir = new File(dir, subDirName);
    subDir.mkdirs();
    return subDir;
  }

  public String getDirPath() {
    return dirPath;
  }

  public File getDir() {
    return dir;
  }
}
