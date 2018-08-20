package capgemini.dao;

import capgemini.dto.CriteriaQueryEmployeeTo;
import capgemini.entities.EmployeeEntity;

import java.util.List;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

    List<EmployeeEntity> findEmployeesByCriteria(CriteriaQueryEmployeeTo criteriaQueryEmployeeTo);
}