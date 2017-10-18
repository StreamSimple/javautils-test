package com.simplifi.it.javautils.testutils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.nio.file.Paths;

public class DirTestWatcherTest {
  @Rule
  public DirTestWatcher dirTestWatcher = new DirTestWatcher();

  @Test
  public void simpleTest() {
    Assert.assertTrue(
      Paths.get("target", DirTestWatcherTest.class.getCanonicalName(), "simpleTest").toFile().exists());
  }

  @Test
  public void simpleTest1() {
    Assert.assertFalse(
      Paths.get("target", DirTestWatcherTest.class.getCanonicalName(), "simpleTest").toFile().exists());
  }
}
