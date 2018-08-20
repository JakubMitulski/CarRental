package capgemini.dao.impl;

import capgemini.dao.PositionDao;
import capgemini.entities.PositionEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDaoImpl extends AbstractDao<PositionEntity, Long> implements PositionDao {

}