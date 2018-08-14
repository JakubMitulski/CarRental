package capgemini.dao;

import capgemini.entities.CarEntity;

import java.util.List;

public interface CarDao extends Dao<CarEntity, Long> {

    void setEmployeeToCar(Long employeeId, Long carId);

    List<CarEntity> findCarsByTypeAndBrand(String type, String Brand);

    List<CarEntity> findCarsByEmployee(Long employeeId);
}
