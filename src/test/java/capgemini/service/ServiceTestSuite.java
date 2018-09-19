package capgemini.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@SpringBootTest
@SuiteClasses({AddressServiceTest.class,
        CarServiceTest.class,
        CustomerServiceTest.class,
        DepartmentServiceTest.class,
        EmployeeServiceTest.class,
        HistoryServiceTest.class,
        PositionServiceTest.class,
        AddressServiceTest.class})
public class ServiceTestSuite {

}
