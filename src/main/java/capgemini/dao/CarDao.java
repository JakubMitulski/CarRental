package capgemini.dao;

import capgemini.entities.CarEntity;
import capgemini.entities.EmployeeEntity;

import java.util.List;

public interface CarDao extends Dao<CarEntity, Long> {

    void addCarToEmployee(CarEntity carEntity, EmployeeEntity employeeEntity);

    List<CarEntity> findCarsByTypeAndBrand(String type, String Brand);

}