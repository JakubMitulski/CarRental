package capgemini.mappers;

import capgemini.dto.EmployeeTo;
import capgemini.entities.CarEntity;
import capgemini.entities.EmployeeEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class EmployeeMapper {

    private static final Long DEFAULT_DEPARTMENT_ID = 0L;

    public static EmployeeTo toEmployeeTo(EmployeeEntity employeeEntity) {
        Long departmentId = DEFAULT_DEPARTMENT_ID;

        if (employeeEntity == null)
            return null;
        if (employeeEntity.getDepartmentEntity() != null){
            departmentId = employeeEntity.getDepartmentEntity().getId();
        }

        return new EmployeeTo.EmployeeToBuilder()
                .withId(employeeEntity.getId())
                .withFirstName(employeeEntity.getFirstName())
                .withLastName(employeeEntity.getLastName())
                .withBirthDate(employeeEntity.getBirthDate())
                .withAddressId(employeeEntity.getAddress().getId())
                .withPositionId(employeeEntity.getPositionEntity().getId())
                .withDepartmentId(departmentId)
                .withCarIds(employeeEntity.getCars().stream().map(CarEntity::getId).collect(Collectors.toSet()))
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

        return employeeEntity;
    }

    public static List<EmployeeTo> map2Tos(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream().map(EmployeeMapper::toEmployeeTo).collect(Collectors.toList());
    }

    public static List<EmployeeEntity> map2Entities(List<EmployeeTo> employeeTos) {
        return employeeTos.stream().map(EmployeeMapper::toEmployeeEntity).collect(Collectors.toList());
    }

}
