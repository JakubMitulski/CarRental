package capgemini.dao;

import capgemini.entities.CarEntity;
import capgemini.entities.EmployeeEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CarDao extends Dao<CarEntity, Long> {

    void addCarToEmployee(CarEntity carEntity, EmployeeEntity employeeEntity);

    List<CarEntity> findCarsByTypeAndBrand(String type, String Brand);

    CarEntity findCarByBrandAndModel(CarEntity carEntity);

    List<CarEntity> findCarsByEmployee(EmployeeEntity employeeEntity);

    List<CarEntity> findCarsRentedByMoreThan10DifferentCustomers();

    List<CarEntity> findCarsRentedInGivenPeriod(Date rentalDate, Date returnDate);
}
