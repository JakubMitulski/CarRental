package capgemini.service;

import capgemini.dto.CustomerTo;

public interface CustomerService {

    CustomerTo findCustomerById(Long id);

    CustomerTo addNewCustomer(CustomerTo customerTo);

    CustomerTo updateCustomer(CustomerTo customerTo);

    void deleteCustomer(CustomerTo customerTo);
}
