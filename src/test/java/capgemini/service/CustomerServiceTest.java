package capgemini.service;

import capgemini.dto.AddressTo;
import capgemini.dto.CustomerTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Test
    @Transactional
    public void shouldFindCustomerById() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("SomeCity")
                .withStreet("SomeStreet")
                .withPostcode("SomeCode")
                .build();
        AddressTo testAddress = addressService.addNewAddress(addressTo);

        CustomerTo customerTo = new CustomerTo.CustomerToBuilder()
                .withFirstName("jan")
                .withLastName("kowalski")
                .withEmail("jan@gmail.com")
                .withBirthDate(new Date(19900101))
                .withCardNumber(1234L)
                .withPhone(777777777L)
                .withAddressId(testAddress.getId())
                .build();
        CustomerTo testCustomer = customerService.addNewCustomer(customerTo);

        //When
        CustomerTo customerById = customerService.findCustomerById(testCustomer.getId());

        //Then
        assertEquals(testCustomer.getLastName(), customerById.getLastName());
        assertEquals(testAddress.getId(), customerById.getAddressId());
    }
}
