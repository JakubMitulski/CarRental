package capgemini.service.impl;

import capgemini.dao.impl.EmployeeDao;
import capgemini.dto.CarTo;
import capgemini.dto.EmployeeTo;
import capgemini.entities.CarEntity;
import capgemini.entities.EmployeeEntity;
import capgemini.mappers.CarMapper;
import capgemini.mappers.EmployeeMapper;
import capgemini.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeRepository;

    @Override
    public EmployeeTo findEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findOne(id);
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public EmployeeTo addNewEmployee(EmployeeTo employeeTo) {
        EmployeeEntity employeeEntity = employeeRepository.save(EmployeeMapper.toEmployeeEntity(employeeTo));
        return EmployeeMapper.toEmployeeTo(employeeEntity);
    }
}
