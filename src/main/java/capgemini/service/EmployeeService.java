package capgemini.service;

import capgemini.dto.CarTo;
import capgemini.dto.CriteriaQueryEmployeeTo;
import capgemini.dto.DepartmentTo;
import capgemini.dto.EmployeeTo;

import java.util.List;

public interface EmployeeService {

    EmployeeTo findEmployeeById(Long id);

    EmployeeTo addNewEmployee(EmployeeTo employeeTo);

    EmployeeTo updateEmployee(EmployeeTo employeeTo);

    void addEmployeeToDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo);

    void removeEmployeeFromDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo);

    List<EmployeeTo> findEmployeesByDepartment(DepartmentTo departmentTo);

    List<EmployeeTo> findEmployeesByDepartmentAndCar(DepartmentTo departmentTo, CarTo carTo);

    List<EmployeeTo> findEmployeesByCriteria(CriteriaQueryEmployeeTo criteriaQueryEmployeeTo);
}
