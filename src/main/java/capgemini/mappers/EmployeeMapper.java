package capgemini.mappers;

import capgemini.dto.EmployeeTo;
import capgemini.entities.EmployeeEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class EmployeeMapper {

    public static EmployeeTo toEmployeeTo(EmployeeEntity employeeEntity) {
        if (employeeEntity == null)
            return null;

        return new EmployeeTo.EmployeeToBuilder()
                .withId(employeeEntity.getId())
                .withFirstName(employeeEntity.getFirstName())
                .withLastName(employeeEntity.getLastName())
                .withBirthDate(employeeEntity.getBirthDate())
                .withAddressTo(AddressMapper.toAddressTo(employeeEntity.getAddress()))
                .withPositionTo(PositionMapper.toPositionTo(employeeEntity.getPositionEntity()))
                .withDepartmentTo(DepartmentMapper.toDepartmentTo(employeeEntity.getDepartmentEntity()))
                .withCarTos(CarMapper.map2Tos(employeeEntity.getCars()))
                .build();
    }

    public static EmployeeEntity toEmployeeEntity(EmployeeTo employeeTo) {
        if (employeeTo == null)
            return null;

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeTo.getId());
        employeeEntity.setFirstName(employeeTo.getFirstName());
        employeeEntity.setLastName(employeeTo.getLastName());
        employeeEntity.setBirthDate(employeeTo.getBirthDate());
        employeeEntity.setAddress(AddressMapper.toAddressEntity(employeeTo.getAddressTo()));
        employeeEntity.setPositionEntity(PositionMapper.toPositionEntity(employeeTo.getPositionTo()));
        employeeEntity.setDepartmentEntity(DepartmentMapper.toDepartmentEntity(employeeTo.getDepartmentTo()));
        employeeEntity.setCars(CarMapper.map2Entities(employeeTo.getCarTos()));

        return employeeEntity;
    }

    public static Set<EmployeeTo> map2Tos(Set<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream().map(EmployeeMapper::toEmployeeTo).collect(Collectors.toSet());
    }

    public static Set<EmployeeEntity> map2Entities(Set<EmployeeTo> employeeTos) {
        return employeeTos.stream().map(EmployeeMapper::toEmployeeEntity).collect(Collectors.toSet());
    }

}
