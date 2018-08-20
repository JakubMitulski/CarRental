package capgemini.mappers;

import capgemini.dto.DepartmentTo;
import capgemini.entities.DepartmentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {

    public static DepartmentTo toDepartmentTo(DepartmentEntity departmentEntity) {
        if (departmentEntity == null)
            return null;

        return new DepartmentTo.DepartmentToBuilder()
                .withId(departmentEntity.getId())
                .withName(departmentEntity.getName())
                .withPhone(departmentEntity.getPhone())
                .withAddressId(departmentEntity.getAddress().getId())
                .build();
    }

    public static DepartmentEntity toDepartmentEntity(DepartmentTo departmentTo) {
        if (departmentTo == null)
            return null;

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(departmentTo.getId());
        departmentEntity.setName(departmentTo.getName());
        departmentEntity.setPhone(departmentTo.getPhone());

        return departmentEntity;
    }

    public static List<DepartmentTo> map2Tos(List<DepartmentEntity> departmentEntities) {
        return departmentEntities.stream().map(DepartmentMapper::toDepartmentTo).collect(Collectors.toList());
    }

    public static List<DepartmentEntity> map2Entities(List<DepartmentTo> departmentTos) {
        return departmentTos.stream().map(DepartmentMapper::toDepartmentEntity).collect(Collectors.toList());
    }
}
