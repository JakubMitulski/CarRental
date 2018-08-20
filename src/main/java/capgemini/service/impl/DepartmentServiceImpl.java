package capgemini.service.impl;

import capgemini.dao.AddressDao;
import capgemini.dao.DepartmentDao;
import capgemini.dto.DepartmentTo;
import capgemini.entities.AddressEntity;
import capgemini.entities.DepartmentEntity;
import capgemini.mappers.DepartmentMapper;
import capgemini.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentRepository;

    @Autowired
    private AddressDao addressRepository;

    @Override
    public DepartmentTo findDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findOne(id);
        return DepartmentMapper.toDepartmentTo(departmentEntity);
    }

    @Override
    public DepartmentTo addNewDepartment(DepartmentTo departmentTo) {
        DepartmentEntity entity = DepartmentMapper.toDepartmentEntity(departmentTo);

        AddressEntity addressEntity = addressRepository.findOne(departmentTo.getAddressId());
        entity.setAddress(addressEntity);
        DepartmentEntity departmentEntity = departmentRepository.save(entity);

        return DepartmentMapper.toDepartmentTo(departmentEntity);
    }

    @Override
    public void deleteDepartment(DepartmentTo departmentTo) {
        departmentRepository.delete(departmentTo.getId());
    }

    @Override
    public DepartmentTo updateDepartment(DepartmentTo departmentTo) {
        DepartmentEntity entity = DepartmentMapper.toDepartmentEntity(departmentTo);

        AddressEntity addressEntity = addressRepository.findOne(departmentTo.getAddressId());
        entity.setAddress(addressEntity);
        DepartmentEntity departmentEntity = departmentRepository.update(entity);

        return DepartmentMapper.toDepartmentTo(departmentEntity);
    }
}
