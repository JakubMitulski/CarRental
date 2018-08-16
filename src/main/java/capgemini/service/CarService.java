package capgemini.service;

import capgemini.dto.CarTo;
import capgemini.dto.EmployeeTo;

import java.util.Set;

public interface CarService {

    CarTo addNewCar(CarTo carTo);

    void deleteCar(CarTo carTo);

    CarTo updateCar(CarTo carTo);

    CarTo findCarById(Long id);

    void addCarToEmployeeResponsibility(CarTo carTo, EmployeeTo employeeTo);

    CarTo findCarByBrandAndModel(CarTo carTo);

    Set<CarTo> findCarsByEmployee(EmployeeTo employeeTo);
}
