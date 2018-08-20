package capgemini.service;

import capgemini.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.util.DateUtil.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class CarServiceTest {

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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HistoryService historyService;

    @Test
    @Transactional
    public void shouldFindCarById() {
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

        //When
        CarTo carById = carService.findCarById(testCar.getId());

        //Then
        assertEquals(testCar.getBrand(), carById.getBrand());
    }

    @Test
    @Transactional
    public void shouldDeleteCar() {
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

        //When
        carService.deleteCar(testCar);

        //Then
        assertNull(carService.findCarById(testCar.getId()));
    }

    @Test
    @Transactional
    public void shouldUpdateCar() {
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

        //When
        testCar.setColor("curcumagold");
        CarTo updatedCar = carService.updateCar(testCar);

        //Then
        assertEquals("curcumagold", carService.findCarById(updatedCar.getId()).getColor());
    }

    @Test
    @Transactional
    public void shouldAddCarToEmployee() {
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

        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosotion = positionService.addNewPosition(positionTo);

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
                .withPositionId(testPosotion.getId())
                .withDepartmentId(testDepartment.getId())
                .withCarIds(new HashSet<>())
                .build();
        EmployeeTo testEmployee = employeeService.addNewEmployee(employeeTo);

        //When
        carService.addCarToEmployeeResponsibility(testCar, testEmployee);

        //Then
        Set<Long> carIds = employeeService.findEmployeeById(testEmployee.getId()).getCarIds();
        assertEquals(1, carIds.size());
    }

    @Test
    @Transactional
    public void shouldFindCarByBrandAndModel() {
        //Given
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
                .withBrand("mazda")
                .withModel("3")
                .withColor("rocketred")
                .withProductionYear(Year.parse("2018"))
                .withEngineCapacity(2000)
                .withHorsePower(160)
                .withMileage(5000)
                .withCarType("hatch")
                .build();
        CarTo testCar2 = carService.addNewCar(carTo2);

        //When
        CarTo carByBrandAndModel = carService.findCarByBrandAndModel(testCar2);

        //Then
        assertEquals("3", carByBrandAndModel.getModel());
        assertEquals("rocketred", carByBrandAndModel.getColor());
    }

    @Test
    @Transactional
    public void shouldFindCarsByEmployee() {
        //Given
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
                .withBrand("mazda")
                .withModel("3")
                .withColor("rocketred")
                .withProductionYear(Year.parse("2018"))
                .withEngineCapacity(2000)
                .withHorsePower(160)
                .withMileage(5000)
                .withCarType("hatch")
                .build();
        CarTo testCar2 = carService.addNewCar(carTo2);

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
        carService.addCarToEmployeeResponsibility(testCar1, testEmployee);
        carService.addCarToEmployeeResponsibility(testCar2, testEmployee);

        //Then
        Set<CarTo> carsByEmployee = carService.findCarsByEmployee(testEmployee);
        assertEquals(2, carsByEmployee.size());
    }

    @Test
    @Transactional
    public void shouldFindCarsRentedByMoreThan10DifferentCustomers() {
        //Given
        buildRentalHistoryFor10DifferentCustomersTest();

        //When
        Set<CarTo> resultSet = carService.findCarsRentedByMoreThan10DifferentCustomers();

        //Then
        assertEquals(1, resultSet.size());
    }

    @Test
    @Transactional
    public void shouldFindCarsRentedInGivenPeriod() {
        //Given
        buildRentalHistoryFor10DifferentCustomersTest();

        //When
        Date rentalDate = new Date(20180810);
        Date returnDate = new Date(20180820);
        Set<CarTo> resultSet = carService.findCarsRentedInGivenPeriod(rentalDate, returnDate);

        //Then
        assertEquals(2, resultSet.size());
    }

    private void buildRentalHistoryFor10DifferentCustomersTest() {
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
                .withBrand("seat")
                .withModel("leon")
                .withColor("red")
                .withProductionYear(Year.parse("2018"))
                .withEngineCapacity(1800)
                .withHorsePower(190)
                .withMileage(5000)
                .withCarType("hatch")
                .build();
        CarTo testCar2 = carService.addNewCar(carTo2);

        CarTo carTo3 = new CarTo.CarToBuilder()
                .withBrand("seat")
                .withModel("ibiza")
                .withColor("red")
                .withProductionYear(Year.parse("2018"))
                .withEngineCapacity(1200)
                .withHorsePower(120)
                .withMileage(1000)
                .withCarType("hatch")
                .build();
        CarTo testCar3 = carService.addNewCar(carTo3);

        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("SomeCity")
                .withStreet("SomeStreet")
                .withPostcode("SomeCode")
                .build();
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("Dept")
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        DepartmentTo department = departmentService.addNewDepartment(departmentTo);

        CustomerTo customerTo1 = new CustomerTo.CustomerToBuilder()
                .withFirstName("jan")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer1 = customerService.addNewCustomer(customerTo1);

        HistoryTo historyTo1 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180830))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer1.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry1 = historyService.addNewHistoryEntry(historyTo1);

        CustomerTo customerTo2 = new CustomerTo.CustomerToBuilder()
                .withFirstName("tomasz")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer2 = customerService.addNewCustomer(customerTo2);

        HistoryTo historyTo2 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180805))
                .withReturnDate(new Date(20180825))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer2.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry2 = historyService.addNewHistoryEntry(historyTo2);

        CustomerTo customerTo3 = new CustomerTo.CustomerToBuilder()
                .withFirstName("paweł")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer3 = customerService.addNewCustomer(customerTo3);

        HistoryTo historyTo3 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180810))
                .withReturnDate(new Date(20180820))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer3.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry3 = historyService.addNewHistoryEntry(historyTo3);

        CustomerTo customerTo4 = new CustomerTo.CustomerToBuilder()
                .withFirstName("andrzej")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer4 = customerService.addNewCustomer(customerTo4);

        HistoryTo historyTo4 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer4.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry4 = historyService.addNewHistoryEntry(historyTo4);

        CustomerTo customerTo5 = new CustomerTo.CustomerToBuilder()
                .withFirstName("szymon")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer5 = customerService.addNewCustomer(customerTo5);

        HistoryTo historyTo5 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer5.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry5 = historyService.addNewHistoryEntry(historyTo5);

        CustomerTo customerTo6 = new CustomerTo.CustomerToBuilder()
                .withFirstName("jakub")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer6 = customerService.addNewCustomer(customerTo6);

        HistoryTo historyTo6 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer6.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry6 = historyService.addNewHistoryEntry(historyTo6);

        CustomerTo customerTo7 = new CustomerTo.CustomerToBuilder()
                .withFirstName("marek")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer7 = customerService.addNewCustomer(customerTo7);

        HistoryTo historyTo7 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer7.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry7 = historyService.addNewHistoryEntry(historyTo7);

        CustomerTo customerTo8 = new CustomerTo.CustomerToBuilder()
                .withFirstName("mateusz")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer8 = customerService.addNewCustomer(customerTo8);

        HistoryTo historyTo8 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer8.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry8 = historyService.addNewHistoryEntry(historyTo8);

        CustomerTo customerTo9 = new CustomerTo.CustomerToBuilder()
                .withFirstName("marcin")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer9 = customerService.addNewCustomer(customerTo9);

        HistoryTo historyTo9 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer9.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry9 = historyService.addNewHistoryEntry(historyTo9);

        CustomerTo customerTo10 = new CustomerTo.CustomerToBuilder()
                .withFirstName("maciej")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer10 = customerService.addNewCustomer(customerTo10);

        HistoryTo historyTo10 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer10.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry10 = historyService.addNewHistoryEntry(historyTo10);

        CustomerTo customerTo11 = new CustomerTo.CustomerToBuilder()
                .withFirstName("łukasz")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer11 = customerService.addNewCustomer(customerTo11);

        HistoryTo historyTo11 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar1.getId())
                .withCustomerId(testCustomer11.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry11 = historyService.addNewHistoryEntry(historyTo11);

        CustomerTo customerTo12 = new CustomerTo.CustomerToBuilder()
                .withFirstName("marian")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer12 = customerService.addNewCustomer(customerTo12);

        HistoryTo historyTo12 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry12 = historyService.addNewHistoryEntry(historyTo12);

        HistoryTo historyTo13 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry13 = historyService.addNewHistoryEntry(historyTo13);

        HistoryTo historyTo14 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry14 = historyService.addNewHistoryEntry(historyTo14);

        HistoryTo historyTo15 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry15 = historyService.addNewHistoryEntry(historyTo15);

        HistoryTo historyTo16 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry16 = historyService.addNewHistoryEntry(historyTo16);

        HistoryTo historyTo17 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry17 = historyService.addNewHistoryEntry(historyTo17);

        HistoryTo historyTo18 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry18 = historyService.addNewHistoryEntry(historyTo18);

        HistoryTo historyTo19 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry19 = historyService.addNewHistoryEntry(historyTo19);

        HistoryTo historyTo20 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry20 = historyService.addNewHistoryEntry(historyTo20);

        HistoryTo historyTo21 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180820))
                .withReturnDate(new Date(20180830))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry21 = historyService.addNewHistoryEntry(historyTo21);

        HistoryTo historyTo22 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180810))
                .withReturnDate(new Date(20180820))
                .withCarId(testCar2.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry22 = historyService.addNewHistoryEntry(historyTo22);

        HistoryTo historyTo23 = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180812))
                .withReturnDate(new Date(20180820))
                .withCarId(testCar3.getId())
                .withCustomerId(testCustomer12.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry23 = historyService.addNewHistoryEntry(historyTo23);
    }
}
