package org.acme;

import org.junit.Before;

import java.util.Arrays;

public class ExtTest {
  @Before
  public void beforeMethod() {
    String currentSuiteName = SuiteRunListener.getCurrentSuiteName();
    if (currentSuiteName != null) {
      System.out.println("Annotations from suite " + currentSuiteName);
      SuiteRunListener.getCurrentSuiteAnnotations().forEach(System.out::println);
    }
    System.out.println("Annotations from class " + this.getClass());
    Arrays.asList(this.getClass().getAnnotations()).forEach(System.out::println);
    System.out.println();
  }
}
