package com.simplifi.it.javautils.testutils;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.IOException;

public class DirTestWatcher extends TestWatcher {
  private String dirPath;
  
  @Override
  protected void starting(Description description) {
    String methodName = description.getMethodName();
    String className = description.getClassName();
    this.dirPath = (new File(".").getAbsolutePath()) + "/target/" + className + "/" + methodName;
    new File(dirPath).mkdirs();
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

  public String getDirPath() {
    return dirPath;
  }

  public File getDir() {
    return new File(dirPath);
  }
}
