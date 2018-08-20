package capgemini.service;

import capgemini.dto.PositionTo;

public interface PositionService {

    PositionTo findPositionById(Long id);

    PositionTo addNewPosition(PositionTo positionTo);

    PositionTo updatePosition(PositionTo positionTo);
}
