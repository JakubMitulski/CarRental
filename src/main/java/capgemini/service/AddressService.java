package capgemini.service;

import capgemini.dto.AddressTo;

public interface AddressService {

    AddressTo findAddressById(Long id);

    AddressTo addNewAddress(AddressTo addressTo);

    AddressTo updateAddress(AddressTo addressTo);
}
