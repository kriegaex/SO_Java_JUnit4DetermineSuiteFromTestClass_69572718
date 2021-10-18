package org.acme;

import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

public class SuiteRunListener extends RunListener {
  private static ThreadLocal<String> currentSuiteName = new ThreadLocal<String>();
  private static ThreadLocal<List<Annotation>> currentSuiteAnnotations = new ThreadLocal<>();

  @Override
  public void testSuiteStarted(Description description) throws Exception {
    super.testSuiteStarted(description);
    final RunWith runWith = description.getAnnotation(RunWith.class);
    if (runWith != null && runWith.value().equals(SuiteWithListener.class)) {
      currentSuiteName.set(description.getDisplayName());
      currentSuiteAnnotations.set(
        description.getAnnotations().stream()
          .filter(annotation -> {
            final Class<? extends Annotation> annotationType = annotation.annotationType();
            return !(annotationType.equals(RunWith.class) || annotationType.equals(SuiteClasses.class));
          })
          .collect(Collectors.toList())
      );
    }
  }

  @Override
  public void testSuiteFinished(Description description) throws Exception {
    super.testSuiteFinished(description);
    final RunWith runWith = description.getAnnotation(RunWith.class);
    if (runWith != null && runWith.value().equals(SuiteWithListener.class)) {
      currentSuiteName.set(null);
      currentSuiteAnnotations.set(null);
    }
  }

  public static String getCurrentSuiteName() {
    return currentSuiteName.get();
  }

  public static List<Annotation> getCurrentSuiteAnnotations() {
    return currentSuiteAnnotations.get();
  }
}
