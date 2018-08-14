package capgemini.service;

import capgemini.dto.CarTo;

import java.util.List;

public interface CarService {

    CarTo addNewCar(CarTo carTo);

    void deleteCar(Long carId);

    CarTo updateCar(CarTo carTo);

    List<CarTo> findCarsByEmployee(Long employeeId);

    CarTo findCarById(Long id);
}
