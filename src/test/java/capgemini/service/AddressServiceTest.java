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

    @Test
    @Transactional
    public void shouldUpdateAddress() {
        //Given
        AddressTo addressTo = new AddressTo.AddressToBuilder()
                .withCity("TestCity")
                .withStreet("TestStreet")
                .withPostcode("TestCode")
                .build();

        AddressTo testAddress = addressService.addNewAddress(addressTo);

        //When
        testAddress.setCity("NewCity");
        testAddress.setStreet("NewStreet");
        testAddress.setPostcode("NewPostcode");
        AddressTo addressById = addressService.updateAddress(testAddress);

        //Then
        assertEquals(testAddress.getStreet(), addressById.getStreet());
        assertEquals(testAddress.getCity(), addressById.getCity());
        assertEquals(testAddress.getPostcode(), addressById.getPostcode());
    }
}
