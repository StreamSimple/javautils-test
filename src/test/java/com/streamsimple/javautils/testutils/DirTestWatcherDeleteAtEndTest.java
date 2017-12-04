package com.streamsimple.javautils.testutils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import java.nio.file.Paths;

public class DirTestWatcherDeleteAtEndTest
{
  @Rule
  public DirTestWatcher dirTestWatcher = new DirTestWatcher.Builder()
      .setDeleteAtEnd(false)
      .build();


  @Test
  public void simpleTest() {
    Assert.assertTrue(
        Paths.get("target", DirTestWatcherDeleteAtEndTest.class.getCanonicalName(), "simpleTest").toFile().exists());
  }

  @Test
  public void simpleTest1() {
    Assert.assertTrue(
        Paths.get("target", DirTestWatcherDeleteAtEndTest.class.getCanonicalName(), "simpleTest").toFile().exists());
  }
}
