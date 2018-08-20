package capgemini.dao.impl;

import capgemini.dao.CarDao;
import capgemini.entities.CarEntity;
import capgemini.entities.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
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
                "select employee from EmployeeEntity employee where employee.id = :employeeId", EmployeeEntity.class);
        query.setParameter("employeeId", employeeEntity.getId());
        EmployeeEntity employee = query.getSingleResult();

        employee.addCar(carEntity);
        entityManager.merge(employee);
    }

    @Override
    public CarEntity findCarByBrandAndModel(CarEntity carEntity) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where car.brand like :brand and car.model like :model", CarEntity.class);
        query.setParameter("brand", carEntity.getBrand());
        query.setParameter("model", carEntity.getModel());
        return query.getSingleResult();
    }

    @Override
    public List<CarEntity> findCarsByEmployee(EmployeeEntity employeeEntity) {
        Query query = entityManager.createQuery(
                "select e.cars from EmployeeEntity e where e.id = :id");
        query.setParameter("id", employeeEntity.getId());
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsRentedByMoreThan10DifferentCustomers() {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select c from CarEntity c " +
                        "where c.id in (" +
                        "select h.carEntity.id from HistoryEntity h " +
                        "group by h.carEntity.id " +
                        "having count(distinct h.customerEntity.id) > 10" +
                        ")", CarEntity.class);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsRentedInGivenPeriod(Date rentalDate, Date returnDate) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select c from CarEntity c join c.historyEntities h " +
                        "where h.rentalDate <= :rentalDate and h.returnDate >= :returnDate", CarEntity.class);
        query.setParameter("rentalDate", rentalDate);
        query.setParameter("returnDate", returnDate);
        return query.getResultList();
    }
}
