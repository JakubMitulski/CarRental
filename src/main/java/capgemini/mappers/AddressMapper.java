package capgemini.mappers;

import capgemini.dto.AddressTo;
import capgemini.entities.AddressEntity;

import java.util.List;
import java.util.stream.Collectors;


public class AddressMapper {

    public static AddressTo toAddressTo(AddressEntity addressEntity) {
        if (addressEntity == null)
            return null;

        return new AddressTo.AddressToBuilder()
                .withId(addressEntity.getId())
                .withCity(addressEntity.getCity())
                .withStreet(addressEntity.getStreet())
                .withPostcode(addressEntity.getPostcode())
                .build();
    }

    public static AddressEntity toAddressEntity(AddressTo addressTo) {
        if (addressTo == null)
            return null;

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressTo.getId());
        addressEntity.setCity(addressTo.getCity());
        addressEntity.setStreet(addressTo.getStreet());
        addressEntity.setPostcode(addressTo.getPostcode());

        return addressEntity;
    }

    public static List<AddressTo> map2Tos(List<AddressEntity> addressEntities) {
        return addressEntities.stream().map(AddressMapper::toAddressTo).collect(Collectors.toList());
    }

    public static List<AddressEntity> map2Entities(List<AddressTo> addressTos) {
        return addressTos.stream().map(AddressMapper::toAddressEntity).collect(Collectors.toList());
    }

}
