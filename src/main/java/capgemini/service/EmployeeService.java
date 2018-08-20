package capgemini.service;

import capgemini.dto.CriteriaQueryEmployeeTo;
import capgemini.dto.EmployeeTo;

import java.util.List;

public interface EmployeeService {

    EmployeeTo findEmployeeById(Long id);

    EmployeeTo addNewEmployee(EmployeeTo employeeTo);

    EmployeeTo updateEmployee(EmployeeTo employeeTo);

    List<EmployeeTo> findEmployeesByCriteria(CriteriaQueryEmployeeTo criteriaQueryEmployeeTo);
}
