package capgemini.service;

import capgemini.dto.AddressTo;
import capgemini.dto.DepartmentTo;
import capgemini.dto.EmployeeTo;
import capgemini.dto.PositionTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Transactional
    public void shouldFindEmployeeById() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestName")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();

        EmployeeTo employeeTo = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressTo(addressTo)
                .withPositionTo(positionTo)
                .withDepartmentTo(departmentTo)
                .withCarTos(new HashSet<>())
                .build();

        EmployeeTo testEmployee = employeeService.addNewEmployee(employeeTo);

        //When
        EmployeeTo employeeById = employeeService.findEmployeeById(testEmployee.getId());

        //Then
        assertEquals(testEmployee.getFirstName(), employeeById.getFirstName());
    }

}
