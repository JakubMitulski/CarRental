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

    @Test
    @Transactional
    public void shouldAddEmployeeToDepartment() {
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

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosition = positionService.addNewPosition(positionTo);

        EmployeeTo employeeTo = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee = employeeService.addNewEmployee(employeeTo);

        //When
        departmentService.addEmployeeToDepartment(testEmployee, testDepartment);

        //Then
        EmployeeTo employeeById = employeeService.findEmployeeById(testEmployee.getId());
        assertEquals(testDepartment.getId(), employeeById.getDepartmentId());
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
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosition = positionService.addNewPosition(positionTo);

        CarTo carTo = new CarTo.CarToBuilder()
                .withBrand("mazda")
                .withModel("6")
                .withColor("gunmetal")
                .withProductionYear(Year.parse("2015"))
                .withEngineCapacity(2300)
                .withHorsePower(180)
                .withMileage(45000)
                .withCarType("limo")
                .build();
        CarTo testCar = carService.addNewCar(carTo);

        HashSet<Long> carIds = new HashSet<>();
        carIds.add(testCar.getId());

        EmployeeTo employeeTo = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(carIds)
                .build();
        EmployeeTo testEmployee = employeeService.addNewEmployee(employeeTo);

        //When
        departmentService.removeEmployeeFromDepartment(testEmployee, testDepartment);

        //Then
        EmployeeTo employeeById = employeeService.findEmployeeById(testEmployee.getId());
        long departmentId = employeeById.getDepartmentId();
        assertEquals(0L, departmentId);
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
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosition = positionService.addNewPosition(positionTo);

        EmployeeTo employeeTo1 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);

        //When
        DepartmentTo departmentById = departmentService.findDepartmentById(testEmployee1.getDepartmentId());
        List<EmployeeTo> employeesByDepartment = departmentService.findEmployeesByDepartment(departmentById);

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
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        DepartmentTo departmentTo1 = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep1")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment1 = departmentService.addNewDepartment(departmentTo1);

        DepartmentTo departmentTo2 = new DepartmentTo.DepartmentToBuilder()
                .withName("TestDep2")
                .withPhone(888888888L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment2 = departmentService.addNewDepartment(departmentTo2);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosition = positionService.addNewPosition(positionTo);

        EmployeeTo employeeTo1 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment1.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test")
                .withLastName("Test")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment2.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);

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

        carService.addCarToEmployeeResponsibility(testCar1, testEmployee1);
        carService.addCarToEmployeeResponsibility(testCar2, testEmployee1);
        carService.addCarToEmployeeResponsibility(testCar1, testEmployee2);

        //When
        DepartmentTo departmentById = departmentService.findDepartmentById(testEmployee1.getDepartmentId());
        List<EmployeeTo> employees = departmentService.findEmployeesByDepartmentAndCar(departmentById, testCar1);

        //Then
        assertEquals(1, employees.size());
    }
}
