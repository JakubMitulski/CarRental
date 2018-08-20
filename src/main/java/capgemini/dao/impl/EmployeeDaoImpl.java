package capgemini.dao.impl;

import capgemini.dao.EmployeeDao;
import capgemini.dto.CriteriaQueryEmployeeTo;
import capgemini.entities.CarEntity;
import capgemini.entities.DepartmentEntity;
import capgemini.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {

    @Override
    public void addEmployeeToDepartment(EmployeeEntity employeeEntity, DepartmentEntity departmentEntity) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select employee from EmployeeEntity employee where employee.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeEntity.getId());
        EmployeeEntity employee = query.getSingleResult();

        employee.setDepartmentEntity(departmentEntity);
        entityManager.merge(employee);
    }

    @Override
    public void removeEmployeeFromDepartment(EmployeeEntity employeeEntity, DepartmentEntity departmentEntity) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select employee from EmployeeEntity employee where employee.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeEntity.getId());
        EmployeeEntity employee = query.getSingleResult();

        employee.setDepartmentEntity(null);
        entityManager.merge(employee);
    }

    @Override
    public List<EmployeeEntity> findEmployeesByDepartment(DepartmentEntity departmentEntity) {
        TypedQuery query = entityManager.createQuery(
                "select e from EmployeeEntity e where departmentEntity.id = :id", EmployeeEntity.class);
        query.setParameter("id", departmentEntity.getId());
        return query.getResultList();
    }

    @Override
    public List<EmployeeEntity> findEmployeesByDepartmentAndCar(DepartmentEntity departmentEntity, CarEntity carEntity) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e " +
                        "from EmployeeEntity e join e.cars car " +
                        "where e.departmentEntity.id = :deptId and car.id= :carId", EmployeeEntity.class);
        query.setParameter("deptId", departmentEntity.getId());
        query.setParameter("carId", carEntity.getId());
        return query.getResultList();
    }

    /**
     * Method returns list of employees which were searched by one or many criteria params included in param object.
     * @param criteriaQueryEmployeeTo is storage for car id, name of employee position and name of rental department
     * @return list of employees
     */
    @Override
    public List<EmployeeEntity> findEmployeesByCriteria(CriteriaQueryEmployeeTo criteriaQueryEmployeeTo) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select e from EmployeeEntity e ");
        boolean canAppendQueryByAnd = false;
        boolean canAppendQueryByWhere = false;

        Long carId = criteriaQueryEmployeeTo.getCarId();
        if (carId != null) {
            queryBuilder.append("join e.cars car where car.id = :carId");
            canAppendQueryByAnd = true;
        } else {
            canAppendQueryByWhere = true;
        }

        String position = criteriaQueryEmployeeTo.getPosition();
        if (position != null) {
            if (canAppendQueryByWhere) {
                queryBuilder.append(" where ");
            }
            if (canAppendQueryByAnd) {
                queryBuilder.append(" and ");
            }
            queryBuilder.append("e.positionEntity.name = :position");
            canAppendQueryByAnd = true;
            canAppendQueryByWhere = false;
        }

        String department = criteriaQueryEmployeeTo.getDepartment();
        if (department != null) {
            if (canAppendQueryByWhere) {
                queryBuilder.append(" where ");
            }
            if (canAppendQueryByAnd) {
                queryBuilder.append(" and ");
            }
            queryBuilder.append("e.departmentEntity.name = :dept");
        }

        TypedQuery<EmployeeEntity> query = entityManager.createQuery(queryBuilder.toString(), EmployeeEntity.class);

        if (carId != null) {
            query.setParameter("carId", criteriaQueryEmployeeTo.getCarId());
        }
        if (position != null) {
            query.setParameter("position", criteriaQueryEmployeeTo.getPosition());
        }
        if (department != null) {
            query.setParameter("dept", criteriaQueryEmployeeTo.getDepartment());
        }

        return query.getResultList();
    }
}