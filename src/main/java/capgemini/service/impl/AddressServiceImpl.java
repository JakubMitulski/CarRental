package capgemini.service.impl;

import capgemini.dao.AddressDao;
import capgemini.dto.AddressTo;
import capgemini.entities.AddressEntity;
import capgemini.mappers.AddressMapper;
import capgemini.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressRepository;

    @Override
    public AddressTo findAddressById(Long id) {
        AddressEntity addressEntity = addressRepository.findOne(id);
        return AddressMapper.toAddressTo(addressEntity);
    }

    @Override
    public AddressTo addNewAddress(AddressTo addressTo) {
        AddressEntity addressEntity = addressRepository.save(AddressMapper.toAddressEntity(addressTo));
        return AddressMapper.toAddressTo(addressEntity);
    }

    @Override
    public AddressTo updateAddress(AddressTo addressTo) {
        AddressEntity addressEntity = addressRepository.update(AddressMapper.toAddressEntity(addressTo));
        return AddressMapper.toAddressTo(addressEntity);
    }
}