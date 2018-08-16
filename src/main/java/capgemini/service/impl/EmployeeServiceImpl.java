package capgemini.service.impl;

import capgemini.dao.EmployeeDao;
import capgemini.dto.EmployeeTo;
import capgemini.entities.EmployeeEntity;
import capgemini.mappers.EmployeeMapper;
import capgemini.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeRepository;

    @Override
    public EmployeeTo findEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findOne(id);
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }

    @Override
    public EmployeeTo addNewEmployee(EmployeeTo employeeTo) {
        EmployeeEntity employeeEntity = employeeRepository.save(EmployeeMapper.toEmployeeEntity(employeeTo));
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }

    @Override
    public EmployeeTo updateEmployee(EmployeeTo employeeTo) {
        EmployeeEntity employeeEntity = employeeRepository.update(EmployeeMapper.toEmployeeEntity(employeeTo));
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }
}