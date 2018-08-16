package capgemini.mappers;

import capgemini.dto.PositionTo;
import capgemini.entities.PositionEntity;

import java.util.List;
import java.util.stream.Collectors;


public class PositionMapper {

    public static PositionTo toPositionTo(PositionEntity positionEntity) {
        if (positionEntity == null)
            return null;

        return new PositionTo.PositionToBuilder()
                .withId(positionEntity.getId())
                .withName(positionEntity.getName())
                .build();
    }

    public static PositionEntity toPositionEntity(PositionTo positionTo) {
        if (positionTo == null)
            return null;

        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setId(positionTo.getId());
        positionEntity.setName(positionTo.getName());

        return positionEntity;
    }

    public static List<PositionTo> map2Tos(List<PositionEntity> positionEntities) {
        return positionEntities.stream().map(PositionMapper::toPositionTo).collect(Collectors.toList());
    }

    public static List<PositionEntity> map2Entities(List<PositionTo> positionTos) {
        return positionTos.stream().map(PositionMapper::toPositionEntity).collect(Collectors.toList());
    }
}
