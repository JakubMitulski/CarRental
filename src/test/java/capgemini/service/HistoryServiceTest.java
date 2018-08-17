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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class HistoryServiceTest {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CarService carService;

    @Test
    @Transactional
    public void shouldFindHistoryEntryById() {
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
        CarTo car = carService.addNewCar(carTo);

        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("SomeCity")
                .withStreet("SomeStreet")
                .withPostcode("SomeCode")
                .build();
        AddressTo address = addressService.addNewAddress(addressTo);

        CustomerTo customerTo = new CustomerTo.CustomerToBuilder()
                .withFirstName("jan")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(address.getId())
                .build();
        CustomerTo customer = customerService.addNewCustomer(customerTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("Dept")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();
        DepartmentTo department = departmentService.addNewDepartment(departmentTo);

        HistoryTo historyTo = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(car.getId())
                .withCustomerId(customer.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry = historyService.addNewHistoryEntry(historyTo);

        //When
        HistoryTo historyById = historyService.findHistoryEntryById(testEntry.getId());

        //Then
        assertEquals(testEntry.getPrice(), historyById.getPrice());
        assertEquals(testEntry.getCarId(), historyById.getCarId());
    }

    @Test
    @Transactional
    public void shouldRemoveHistoryEntryWhenRemovingCar() {
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
                .withCity("SomeCity")
                .withStreet("SomeStreet")
                .withPostcode("SomeCode")
                .build();
        AddressTo address = addressService.addNewAddress(addressTo);

        CustomerTo customerTo = new CustomerTo.CustomerToBuilder()
                .withFirstName("jan")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(address.getId())
                .build();
        CustomerTo customer = customerService.addNewCustomer(customerTo);

        DepartmentTo departmentTo = new DepartmentTo.DepartmentToBuilder()
                .withName("Dept")
                .withPhone(777777777L)
                .withAddressTo(addressTo)
                .build();
        DepartmentTo department = departmentService.addNewDepartment(departmentTo);

        HistoryTo historyTo = new HistoryTo.HistoryToBuilder()
                .withPrice(1000)
                .withRentalDate(new Date(20180801))
                .withReturnDate(new Date(20180810))
                .withCarId(testCar.getId())
                .withCustomerId(customer.getId())
                .withRentalDepartmentId(department.getId())
                .withReturnDepartmentId(department.getId())
                .build();
        HistoryTo testEntry = historyService.addNewHistoryEntry(historyTo);

        //When
        carService.deleteCar(testCar);

        //Then
        assertNull(historyService.findHistoryEntryById(testEntry.getId()));
    }
}
