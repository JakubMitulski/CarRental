package capgemini.service.impl;

import capgemini.dao.PositionDao;
import capgemini.dto.PositionTo;
import capgemini.entities.PositionEntity;
import capgemini.mappers.PositionMapper;
import capgemini.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionRepository;

    @Override
    public PositionTo findPositionById(Long id) {
        PositionEntity positionEntity = positionRepository.findOne(id);
        return PositionMapper.toPositionTo(positionEntity);
    }

    @Override
    public PositionTo addNewPosition(PositionTo positionTo) {
        PositionEntity positionEntity = positionRepository.save(PositionMapper.toPositionEntity(positionTo));
        return PositionMapper.toPositionTo(positionEntity);
    }

    @Override
    public PositionTo updatePosition(PositionTo positionTo) {
        PositionEntity positionEntity = positionRepository.update(PositionMapper.toPositionEntity(positionTo));
        return PositionMapper.toPositionTo(positionEntity);
    }
}