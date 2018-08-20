package capgemini.service.impl;

import capgemini.dao.*;
import capgemini.dto.EmployeeTo;
import capgemini.entities.*;
import capgemini.mappers.EmployeeMapper;
import capgemini.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public EmployeeTo updateEmployee(EmployeeTo employeeTo) {
        EmployeeEntity employeeEntity = employeeRepository.update(EmployeeMapper.toEmployeeEntity(employeeTo));
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }
}