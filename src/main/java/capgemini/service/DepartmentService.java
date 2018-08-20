package capgemini.service;

import capgemini.dto.DepartmentTo;

public interface DepartmentService {

    DepartmentTo findDepartmentById(Long id);

    DepartmentTo addNewDepartment(DepartmentTo departmentTo);

    void deleteDepartment(DepartmentTo departmentTo);

    DepartmentTo updateDepartment(DepartmentTo departmentTo);
}
