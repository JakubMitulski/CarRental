package capgemini.dao.impl;

import capgemini.dao.CarDao;
import capgemini.entities.CarEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public void setEmployeeToCar(Long employeeId, Long carId) {

    }

    @Override
    public List<CarEntity> findCarsByTypeAndBrand(String type, String Brand) {
        return null;
    }

    @Override
    public List<CarEntity> findCarsByEmployee(Long employeeId) {
        TypedQuery<CarEntity> query = entityManager.createNamedQuery(
                "select car_id from responsibilities where employee_id = employeeId", CarEntity.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }
}
