package capgemini.dao;

import capgemini.entities.CarEntity;
import capgemini.entities.DepartmentEntity;
import capgemini.entities.EmployeeEntity;

import java.util.List;

public interface DepartmentDao extends Dao<DepartmentEntity, Long> {

    void addEmployeeToDepartment(EmployeeEntity employeeEntity, DepartmentEntity departmentEntity);

    void removeEmployeeFromDepartment(EmployeeEntity employeeEntity, DepartmentEntity departmentEntity);

    List<EmployeeEntity> findEmployeesByDepartment(DepartmentEntity departmentEntity);

    List<EmployeeEntity> findEmployeesByDepartmentAndCar(DepartmentEntity departmentEntity, CarEntity carEntity);
}