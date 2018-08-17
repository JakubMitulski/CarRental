package capgemini.dao.impl;

import capgemini.dao.CustomerDao;
import capgemini.entities.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends AbstractDao<CustomerEntity, Long> implements CustomerDao {

}