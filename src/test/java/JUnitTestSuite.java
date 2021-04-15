import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.soen6441.controller.JUnitTestSuiteController;
import org.soen6441.model.JUnitTestSuiteModel;
import org.soen6441.model.orders.JUnitTestSuiteOrders;
import org.soen6441.strategypattern.JUnitTestSuiteStrategy;
import org.soen6441.utility.state.JunitTestSuiteState;

/**
 * JUnit Test Suite Class to run all the test suits in each test folder. 
 */
@RunWith(Suite.class)
@SuiteClasses({JUnitTestSuiteController.class, JUnitTestSuiteModel.class,JUnitTestSuiteOrders.class,JunitTestSuiteState.class,JUnitTestSuiteStrategy.class})
public class JUnitTestSuite {

}
