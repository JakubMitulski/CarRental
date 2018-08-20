package capgemini.service.impl;

import capgemini.dao.*;
import capgemini.dto.CarTo;
import capgemini.dto.CriteriaQueryEmployeeTo;
import capgemini.dto.DepartmentTo;
import capgemini.dto.EmployeeTo;
import capgemini.entities.*;
import capgemini.mappers.CarMapper;
import capgemini.mappers.DepartmentMapper;
import capgemini.mappers.EmployeeMapper;
import capgemini.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeRepository;

    @Autowired
    private AddressDao addressRepository;

    @Autowired
    private PositionDao positionRepository;

    @Autowired
    private DepartmentDao departmentRepository;

    @Autowired
    private CarDao carRepository;

    @Override
    public EmployeeTo findEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findOne(id);
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }

    @Override
    public EmployeeTo addNewEmployee(EmployeeTo employeeTo) {
        EmployeeEntity entity = EmployeeMapper.toEmployeeEntity(employeeTo);

        AddressEntity addressEntity = addressRepository.findOne(employeeTo.getAddressId());
        entity.setAddress(addressEntity);

        PositionEntity positionEntity = positionRepository.findOne(employeeTo.getPositionId());
        entity.setPositionEntity(positionEntity);

        DepartmentEntity departmentEntity = departmentRepository.findOne(employeeTo.getDepartmentId());
        entity.setDepartmentEntity(departmentEntity);

        Set<CarEntity> carEntities = new HashSet<>();
        for (long carId : employeeTo.getCarIds()) {
            CarEntity carEntity = carRepository.findOne(carId);
            carEntities.add(carEntity);
        }
        entity.setCars(carEntities);

        EmployeeEntity employeeEntity = employeeRepository.save(entity);
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }

    @Override
    public void addEmployeeToDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo) {
        employeeRepository.addEmployeeToDepartment(
                EmployeeMapper.toEmployeeEntity(employeeTo),
                DepartmentMapper.toDepartmentEntity(departmentTo));
    }

    @Override
    public void removeEmployeeFromDepartment(EmployeeTo employeeTo, DepartmentTo departmentTo) {
        employeeRepository.removeEmployeeFromDepartment(
                EmployeeMapper.toEmployeeEntity(employeeTo),
                DepartmentMapper.toDepartmentEntity(departmentTo));
    }

    @Override
    public List<EmployeeTo> findEmployeesByDepartment(DepartmentTo departmentTo) {
        List<EmployeeEntity> employeeEntities = employeeRepository
                .findEmployeesByDepartment(DepartmentMapper.toDepartmentEntity(departmentTo));
        return EmployeeMapper.map2Tos(employeeEntities);
    }

    @Override
    public List<EmployeeTo> findEmployeesByDepartmentAndCar(DepartmentTo departmentTo, CarTo carTo) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findEmployeesByDepartmentAndCar(
                DepartmentMapper.toDepartmentEntity(departmentTo),
                CarMapper.toCarEntity(carTo));
        return EmployeeMapper.map2Tos(employeeEntities);
    }

    @Override
    public EmployeeTo updateEmployee(EmployeeTo employeeTo) {
        EmployeeEntity employeeEntity = employeeRepository.update(EmployeeMapper.toEmployeeEntity(employeeTo));
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }

    @Override
    public List<EmployeeTo> findEmployeesByCriteria(CriteriaQueryEmployeeTo criteriaQueryEmployeeTo) {
        List<EmployeeEntity> employeesByCriteria = employeeRepository.findEmployeesByCriteria(criteriaQueryEmployeeTo);
        return EmployeeMapper.map2Tos(employeesByCriteria);
    }
}