package com.simplifi.it.javautils.testutils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

public class DirTestWatcherTest {
  @Rule
  public DirTestWatcher dirTestWatcher = new DirTestWatcher();

  @Test
  public void simpleTest() {
    Assert.assertTrue(
      new File(String.format("target/%s/%s", DirTestWatcherTest.class.getCanonicalName(), "simpleTest")).
        exists());
  }

  @Test
  public void simpleTest1() {
    Assert.assertFalse(
      new File(String.format("target/%s/%s", DirTestWatcherTest.class.getCanonicalName(), "simpleTest")).
        exists());
  }
}
