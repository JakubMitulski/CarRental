package capgemini.dao.impl;

import capgemini.dao.DepartmentDao;
import capgemini.entities.CarEntity;
import capgemini.entities.DepartmentEntity;
import capgemini.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DepartmentDaoImpl extends AbstractDao<DepartmentEntity, Long> implements DepartmentDao {

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
                        "where (e.departmentEntity.id = :deptId and car.id= :carId)", EmployeeEntity.class);
        query.setParameter("deptId", departmentEntity.getId());
        query.setParameter("carId", carEntity.getId());
        return query.getResultList();
    }
}