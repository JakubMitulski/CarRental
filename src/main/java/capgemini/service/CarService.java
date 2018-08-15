package capgemini.service;

import capgemini.dto.CarTo;
import capgemini.dto.EmployeeTo;

public interface CarService {

    CarTo addNewCar(CarTo carTo);

    void deleteCar(Long carId);

    CarTo updateCar(CarTo carTo);

    CarTo findCarById(Long id);

    void addCarToEmployeeResponsibility(CarTo carTo, EmployeeTo employeeTo);
}
