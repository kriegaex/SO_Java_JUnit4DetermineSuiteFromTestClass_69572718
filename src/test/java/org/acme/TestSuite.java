package org.acme;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(SuiteWithListener.class)
@SuiteClasses({
  FirstTest.class,
  SecondTest.class
})
@TestSuiteAnnotation
public class TestSuite {}
