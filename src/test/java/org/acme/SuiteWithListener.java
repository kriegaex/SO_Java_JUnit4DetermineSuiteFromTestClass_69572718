package org.acme;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.util.List;

public class SuiteWithListener extends Suite {
  public SuiteWithListener(Class<?> klass, RunnerBuilder builder) throws InitializationError {
    super(klass, builder);
  }

  public SuiteWithListener(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
    super(builder, classes);
  }

  protected SuiteWithListener(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
    super(klass, suiteClasses);
  }

  protected SuiteWithListener(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
    super(builder, klass, suiteClasses);
  }

  protected SuiteWithListener(Class<?> klass, List<Runner> runners) throws InitializationError {
    super(klass, runners);
  }

  @Override
  public void run(RunNotifier notifier) {
    notifier.addListener(new SuiteRunListener());
    super.run(notifier);
  }
}
