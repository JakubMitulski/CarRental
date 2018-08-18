package capgemini.service;

import capgemini.dto.AddressTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    @Transactional
    public void shouldFindAddressById() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        AddressTo testAddress = addressService.addNewAddress(addressTo);

        //When
        AddressTo addressById = addressService.findAddressById(testAddress.getId());

        //Then
        assertEquals(testAddress.getStreet(), addressById.getStreet());
    }
}