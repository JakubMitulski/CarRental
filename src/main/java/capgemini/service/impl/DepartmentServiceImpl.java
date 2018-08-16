package capgemini.service.impl;

import capgemini.dao.DepartmentDao;
import capgemini.dto.CarTo;
import capgemini.dto.DepartmentTo;
import capgemini.dto.EmployeeTo;
import capgemini.entities.DepartmentEntity;
import capgemini.entities.EmployeeEntity;
import capgemini.mappers.CarMapper;
import capgemini.mappers.DepartmentMapper;
import capgemini.mappers.EmployeeMapper;
import capgemini.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentRepository;

    @Override
    public DepartmentTo findDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findOne(id);
        return DepartmentMapper.toDepartmentTo(departmentEntity);
    }

    @Override
    public DepartmentTo addNewDepartment(DepartmentTo departmentTo) {
        DepartmentEntity departmentEntity = departmentRepository
                .save(DepartmentMapper.toDepartmentEntity(departmentTo));
        return DepartmentMapper.toDepartmentTo(departmentEntity);
    }

    @Override
    public void deleteDepartment(DepartmentTo departmentTo) {
        departmentRepository.delete(departmentTo.getId());
    }

    @Override
    public DepartmentTo updateDepartment(DepartmentTo departmentTo) {
        DepartmentEntity departmentEntity = departmentRepository.update(
                DepartmentMapper.toDepartmentEntity(departmentTo));
        return DepartmentMapper.toDepartmentTo(departmentEntity);
    }

    @Override
    public void addEmployeeToDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo) {
        departmentRepository.addEmployeeToDepartment(
                EmployeeMapper.toEmployeeEntity(employeeTo),
                DepartmentMapper.toDepartmentEntity(departmentTo));
    }

    @Override
    public void removeEmployeeFromDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo) {
        departmentRepository.removeEmployeeFromDepartment(
                EmployeeMapper.toEmployeeEntity(employeeTo),
                DepartmentMapper.toDepartmentEntity(departmentTo));
    }

    @Override
    public List<EmployeeTo> findEmployeesByDepartment(DepartmentTo departmentTo) {
        List<EmployeeEntity> employeeEntities = departmentRepository
                .findEmployeesByDepartment(DepartmentMapper.toDepartmentEntity(departmentTo));
        return EmployeeMapper.map2Tos(employeeEntities);
    }

    @Override
    public List<EmployeeTo> findEmployeesByDepartmentAndCar(DepartmentTo departmentTo, CarTo carTo) {
        List<EmployeeEntity> employeeEntities = departmentRepository.findEmployeesByDepartmentAndCar(
                DepartmentMapper.toDepartmentEntity(departmentTo),
                CarMapper.toCarEntity(carTo));
        return EmployeeMapper.map2Tos(employeeEntities);
    }
}
