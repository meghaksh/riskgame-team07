import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.soen6441.model.JUnitTestSuiteModel;
import org.soen6441.model.orders.JUnitTestSuiteOrders;
import org.soen6441.utility.state.JunitTestSuiteState;

@RunWith(Suite.class)
@SuiteClasses({JUnitTestSuiteModel.class,JUnitTestSuiteOrders.class,JunitTestSuiteState.class})
public class JUnitTestSuite {

}
