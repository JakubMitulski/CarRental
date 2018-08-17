package capgemini.service.impl;

import capgemini.dao.AddressDao;
import capgemini.dao.CustomerDao;
import capgemini.dto.CustomerTo;
import capgemini.entities.AddressEntity;
import capgemini.entities.CustomerEntity;
import capgemini.mappers.CustomerMapper;
import capgemini.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerRepository;

    @Autowired
    private AddressDao addressRepository;

    @Override
    public CustomerTo findCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        return CustomerMapper.toCustomerTo(customerEntity);
    }

    @Override
    public CustomerTo addNewCustomer(CustomerTo customerTo) {
        CustomerEntity entity = CustomerMapper.toCustomerEntity(customerTo);

        AddressEntity addressEntity = addressRepository.findOne(customerTo.getAddressId());
        entity.setAddressEntity(addressEntity);

        CustomerEntity customerEntity = customerRepository.save(entity);
        return CustomerMapper.toCustomerTo(customerEntity);
    }

    @Override
    public CustomerTo updateCustomer(CustomerTo customerTo) {
        CustomerEntity customerEntity = customerRepository.update(CustomerMapper.toCustomerEntity(customerTo));
        return CustomerMapper.toCustomerTo(customerEntity);
    }
}