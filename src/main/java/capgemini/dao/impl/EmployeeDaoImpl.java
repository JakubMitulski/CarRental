package capgemini.dao.impl;

import capgemini.dao.EmployeeDao;
import capgemini.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

}