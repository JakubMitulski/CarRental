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
}
