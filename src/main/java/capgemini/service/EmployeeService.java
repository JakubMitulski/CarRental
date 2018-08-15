package capgemini.service;

import capgemini.dto.EmployeeTo;

public interface EmployeeService {

    EmployeeTo findEmployeeById(Long id);

    EmployeeTo addNewEmployee(EmployeeTo employeeTo);
}
