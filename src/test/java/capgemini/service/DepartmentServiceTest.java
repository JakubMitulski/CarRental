package capgemini.service;

import capgemini.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;
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

    @Test
    @Transactional
    public void shouldFindDepartmentById() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
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

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
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

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();

        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        //When
        testDepartment.setName("testtesttest");
        DepartmentTo updatedDepartment = departmentService.updateDepartment(testDepartment);

        //Then
        assertEquals("testtesttest", departmentService
                .findDepartmentById(updatedDepartment.getId()).getName());
    }

    @Test
    @Transactional
    public void shouldAddEmployeeToDepartment() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();

        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
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
        departmentService.addEmployeeToDepartment(testEmployee, testDepartment);

        //Then
        EmployeeTo employeeById = employeeService.findEmployeeById(testEmployee.getId());
        assertEquals(testDepartment.getName(), employeeById.getDepartmentTo().getName());
    }

    @Test
    @Transactional
    public void shouldRemoveEmployeeFromDepartment() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();

        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
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
        departmentService.removeEmployeeFromDepartment(testEmployee, testDepartment);

        //Then
        EmployeeTo employeeById = employeeService.findEmployeeById(testEmployee.getId());
        assertNull(employeeById.getDepartmentTo());
    }

    @Test
    @Transactional
    public void shouldFindEmployeesByDepartment() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();

        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();

        EmployeeTo employeeTo1 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressTo(addressTo)
                .withPositionTo(positionTo)
                .withDepartmentTo(null)
                .withCarTos(new HashSet<>())
                .build();

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressTo(addressTo)
                .withPositionTo(positionTo)
                .withDepartmentTo(null)
                .withCarTos(new HashSet<>())
                .build();

        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);
        testEmployee1.setDepartmentTo(testDepartment);
        testEmployee2.setDepartmentTo(testDepartment);
        employeeService.updateEmployee(testEmployee1);
        employeeService.updateEmployee(testEmployee2);

        //When
        List<EmployeeTo> employeesByDepartment = departmentService
                .findEmployeesByDepartment(testEmployee1.getDepartmentTo());

        //Then
        assertEquals(2, employeesByDepartment.size());
    }

    @Test
    @Transactional
    public void shouldFindEmployeesByDepartmentAndCar() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();

        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();

        EmployeeTo employeeTo1 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressTo(addressTo)
                .withPositionTo(positionTo)
                .withDepartmentTo(null)
                .withCarTos(new HashSet<>())
                .build();

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressTo(addressTo)
                .withPositionTo(positionTo)
                .withDepartmentTo(null)
                .withCarTos(new HashSet<>())
                .build();

        CarTo carTo1 = new CarTo.CarToBuilder()
                .withBrand("mazda")
                .withModel("6")
                .withColor("gunmetal")
                .withProductionYear(Year.parse("2015"))
                .withEngineCapacity(2300)
                .withHorsePower(180)
                .withMileage(45000)
                .withCarType("limo")
                .build();
        CarTo testCar1 = carService.addNewCar(carTo1);

        CarTo carTo2 = new CarTo.CarToBuilder()
                .withBrand("infinity")
                .withModel("fx30")
                .withColor("pearlblack")
                .withProductionYear(Year.parse("2012"))
                .withEngineCapacity(4200)
                .withHorsePower(280)
                .withMileage(105000)
                .withCarType("suv")
                .build();
        CarTo testCar2 = carService.addNewCar(carTo2);

        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);
        testEmployee1.setDepartmentTo(testDepartment);
        testEmployee2.setDepartmentTo(testDepartment);
        employeeService.updateEmployee(testEmployee1);
        employeeService.updateEmployee(testEmployee2);
        carService.addCarToEmployeeResponsibility(testCar1, testEmployee1);
        carService.addCarToEmployeeResponsibility(testCar2, testEmployee1);
        carService.addCarToEmployeeResponsibility(testCar1, testEmployee2);

        //When
        List<EmployeeTo> employees = departmentService
                .findEmployeesByDepartmentAndCar(testEmployee1.getDepartmentTo(), testCar1);

        //Then
        assertEquals(2, employees.size());
    }
}
