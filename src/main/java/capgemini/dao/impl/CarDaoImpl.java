package capgemini.dao.impl;

import capgemini.dao.CarDao;
import capgemini.entities.CarEntity;
import capgemini.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public List<CarEntity> findCarsByTypeAndBrand(String type, String Brand) {
        return null;
    }

    @Override
    public void addCarToEmployee(CarEntity carEntity, EmployeeEntity employeeEntity) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery(
                "select e from EmployeeEntity e where e.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeEntity.getId());
        EmployeeEntity employee = query.getSingleResult();

        employee.addCar(carEntity);
        entityManager.merge(employee);
    }

}
