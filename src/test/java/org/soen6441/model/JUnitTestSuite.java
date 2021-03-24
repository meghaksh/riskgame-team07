package org.soen6441.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * JUnit Test Suite Class to run all the Test cases
 */
@RunWith(Suite.class)
@SuiteClasses({ GameModelTest.class, MapTest.class, OrderTest.class })
public class JUnitTestSuite {

}
