package org.soen6441.strategypattern;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AggresivePlayerStrategyTest.class, BenevolentPlayerStrategyTest.class, CheaterPlayerStrategyTest.class,
		RandomPlayerStrategyTest.class })
public class JUnitTestSuiteStrategy {

}
