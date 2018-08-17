package capgemini.dao.impl;

import capgemini.dao.HistoryDao;
import capgemini.entities.HistoryEntity;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl extends AbstractDao<HistoryEntity, Long> implements HistoryDao {

}