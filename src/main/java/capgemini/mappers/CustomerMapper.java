package capgemini.mappers;

import capgemini.dao.AddressDao;
import capgemini.dto.CustomerTo;
import capgemini.entities.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public class CustomerMapper {

    @Autowired
    private static AddressDao addressRepository;

    public static CustomerTo toCustomerTo(CustomerEntity customerEntity) {
        if (customerEntity == null)
            return null;

        return new CustomerTo.CustomerToBuilder()
                .withId(customerEntity.getId())
                .withFirstName(customerEntity.getFirstName())
                .withLastName(customerEntity.getLastName())
                .withEmail(customerEntity.getEmail())
                .withBirthDate(customerEntity.getBirthDate())
                .withCardNumber(customerEntity.getCardNumber())
                .withPhone(customerEntity.getPhone())
                .withAddressId(customerEntity.getAddressEntity().getId())
                .build();
    }

    public static CustomerEntity toCustomerEntity(CustomerTo customerTo) {
        if (customerTo == null)
            return null;

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerTo.getId());
        customerEntity.setFirstName(customerTo.getFirstName());
        customerEntity.setLastName(customerTo.getLastName());
        customerEntity.setEmail(customerTo.getEmail());
        customerEntity.setBirthDate(customerTo.getBirthDate());
        customerEntity.setCardNumber(customerTo.getCardNumber());
        customerEntity.setPhone(customerTo.getPhone());

        return customerEntity;
    }

    public static List<CustomerTo> map2Tos(List<CustomerEntity> customerEntities) {
        return customerEntities.stream().map(CustomerMapper::toCustomerTo).collect(Collectors.toList());
    }

    public static List<CustomerEntity> map2Entities(List<CustomerTo> customerTos) {
        return customerTos.stream().map(CustomerMapper::toCustomerEntity).collect(Collectors.toList());
    }
}
