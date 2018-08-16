package capgemini.service;

import capgemini.dto.CarTo;
import capgemini.dto.DepartmentTo;
import capgemini.dto.EmployeeTo;

import java.util.List;

public interface DepartmentService {

    DepartmentTo findDepartmentById(Long id);

    DepartmentTo addNewDepartment(DepartmentTo departmentTo);

    void deleteDepartment(DepartmentTo departmentTo);

    DepartmentTo updateDepartment(DepartmentTo departmentTo);

    void addEmployeeToDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo);

    void removeEmployeeFromDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo);

    List<EmployeeTo> findEmployeesByDepartment(DepartmentTo departmentTo);

    List<EmployeeTo> findEmployeesByDepartmentAndCar(DepartmentTo departmentTo, CarTo carTo);
}
