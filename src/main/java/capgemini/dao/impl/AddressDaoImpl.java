package capgemini.dao.impl;

import capgemini.dao.AddressDao;
import capgemini.entities.AddressEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends AbstractDao<AddressEntity, Long> implements AddressDao {

}