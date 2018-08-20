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

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class EmployeeServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Test
    @Transactional
    public void shouldFindEmployeeById() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosition = positionService.addNewPosition(positionTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("TestName")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo testDepartment = departmentService.addNewDepartment(departmentTo);

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
        EmployeeTo employeeById = employeeService.findEmployeeById(testEmployee.getId());

        //Then
        assertEquals(testEmployee.getFirstName(), employeeById.getFirstName());
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
        employeeService.addEmployeeToDepartment(testEmployee, testDepartment);

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
        employeeService.removeEmployeeFromDepartment(testEmployee, testDepartment);

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
        List<EmployeeTo> employeesByDepartment = employeeService.findEmployeesByDepartment(departmentById);

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
        List<EmployeeTo> employees = employeeService.findEmployeesByDepartmentAndCar(departmentById, testCar1);

        //Then
        assertEquals(1, employees.size());
    }

    @Test
    @Transactional
    public void shouldFindEmployeeByAllParamsInCriteriaQuery() {
        //Given
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
                .withFirstName("Test1")
                .withLastName("Test1")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);

        HashSet<Long> carIds = new HashSet<>();
        carIds.add(testCar.getId());

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test2")
                .withLastName("Test2")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(carIds)
                .build();
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);

        CriteriaQueryEmployeeTo criteriaQueryEmployeeTo = new CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder()
                .withCarId(testCar.getId())
                .withDepartmentName(testDepartment.getName())
                .withPositionName(testPosition.getName())
                .build();

        //When
        List<EmployeeTo> employeesByCriteria = employeeService.findEmployeesByCriteria(criteriaQueryEmployeeTo);

        //Then
        assertEquals(1, employeesByCriteria.size());
        assertEquals("Test2", employeesByCriteria.get(0).getLastName());
    }

    @Test
    @Transactional
    public void shouldFindEmployeeByCarInCriteriaQuery() {
        //Given
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


        HashSet<Long> carIds = new HashSet<>();
        carIds.add(testCar.getId());

        EmployeeTo employeeTo1 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test1")
                .withLastName("Test1")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test2")
                .withLastName("Test2")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(carIds)
                .build();
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);

        CriteriaQueryEmployeeTo criteriaQueryEmployeeTo = new CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder()
                .withCarId(testCar.getId())
                .build();

        //When
        List<EmployeeTo> employeesByCriteria = employeeService.findEmployeesByCriteria(criteriaQueryEmployeeTo);

        //Then
        assertEquals(1, employeesByCriteria.size());
        assertEquals("Test2", employeesByCriteria.get(0).getLastName());
    }

    @Test
    @Transactional
    public void shouldFindEmployeeByDepartmentInCriteriaQuery() {
        //Given
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
                .withFirstName("Test1")
                .withLastName("Test1")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test2")
                .withLastName("Test2")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);

        CriteriaQueryEmployeeTo criteriaQueryEmployeeTo = new CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder()
                .withDepartmentName(testDepartment.getName())
                .build();

        //When
        List<EmployeeTo> employeesByCriteria = employeeService.findEmployeesByCriteria(criteriaQueryEmployeeTo);

        //Then
        assertEquals(2, employeesByCriteria.size());
    }

    @Test
    @Transactional
    public void shouldFindEmployeeByPositionInCriteriaQuery() {
        //Given
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
                .withFirstName("Test1")
                .withLastName("Test1")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee1 = employeeService.addNewEmployee(employeeTo1);

        EmployeeTo employeeTo2 = new EmployeeTo.EmployeeToBuilder()
                .withFirstName("Test2")
                .withLastName("Test2")
                .withBirthDate(now())
                .withAddressId(testAddress.getId())
                .withPositionId(testPosition.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee2 = employeeService.addNewEmployee(employeeTo2);

        CriteriaQueryEmployeeTo criteriaQueryEmployeeTo = new CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder()
                .withPositionName(testPosition.getName())
                .build();

        //When
        List<EmployeeTo> employeesByCriteria = employeeService.findEmployeesByCriteria(criteriaQueryEmployeeTo);

        //Then
        assertEquals(2, employeesByCriteria.size());
    }
}
