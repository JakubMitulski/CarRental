package capgemini.service;

import capgemini.dto.AddressTo;
import capgemini.dto.DepartmentTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PositionService positionService;

    @Test
    @Transactional
    public void shouldFindDepartmentById() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        //When
        DepartmentTo departmentById = departmentService.findDepartmentById(testDepartment.getId());

        //Then
        assertEquals(testDepartment.getName(), departmentById.getName());
    }

    @Test
    @Transactional
    public void shouldDeleteDepartment() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        //When
        departmentService.deleteDepartment(testDepartment);

        //Then
        assertNull(departmentService.findDepartmentById(testDepartment.getId()));
    }

    @Test
    @Transactional
    public void shouldUpdateDepartment() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        //When
        testDepartment.setName("testtesttest");
        DepartmentTo updatedDepartment = departmentService.updateDepartment(testDepartment);

        //Then
        assertEquals("testtesttest", departmentService
                .findDepartmentById(updatedDepartment.getId()).getName());
    }
}
