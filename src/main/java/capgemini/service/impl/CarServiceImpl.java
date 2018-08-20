package capgemini.service.impl;

import capgemini.dao.CarDao;
import capgemini.dto.CarTo;
import capgemini.dto.EmployeeTo;
import capgemini.entities.CarEntity;
import capgemini.mappers.CarMapper;
import capgemini.mappers.EmployeeMapper;
import capgemini.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carRepository;

    @Override
    public CarTo findCarById(Long id) {
        CarEntity carEntity = carRepository.findOne(id);
        return CarMapper.toCarTo(carEntity);
    }

    @Override
    public CarTo addNewCar(CarTo carTo) {
        CarEntity carEntity = carRepository.save(CarMapper.toCarEntity(carTo));
        return CarMapper.toCarTo(carEntity);
    }

    @Override
    public void deleteCar(CarTo carTo) {
        carRepository.delete(carTo.getId());
    }

    @Override
    public CarTo updateCar(CarTo carTo) {
        CarEntity carEntity = carRepository.update(CarMapper.toCarEntity(carTo));
        return CarMapper.toCarTo(carEntity);
    }

    @Override
    public void addCarToEmployeeResponsibility(CarTo carTo, EmployeeTo employeeTo) {
        carRepository.addCarToEmployee(CarMapper.toCarEntity(carTo), EmployeeMapper.toEmployeeEntity(employeeTo));
    }

    @Override
    public CarTo findCarByBrandAndModel(CarTo carTo) {
        CarEntity carByBrandAndModel = carRepository.findCarByBrandAndModel(CarMapper.toCarEntity(carTo));
        return CarMapper.toCarTo(carByBrandAndModel);
    }

    @Override
    public Set<CarTo> findCarsByEmployee(EmployeeTo employeeTo) {
        List<CarEntity> carEntities = carRepository.findCarsByEmployee(EmployeeMapper.toEmployeeEntity(employeeTo));
        Set<CarEntity> resultSet = new HashSet<>(carEntities);
        return CarMapper.map2Tos(resultSet);
    }

    @Override
    public Set<CarTo> findCarsRentedByMoreThan10DifferentCustomers() {
        List<CarEntity> carEntities = carRepository.findCarsRentedByMoreThan10DifferentCustomers();
        Set<CarEntity> resultSet = new HashSet<>(carEntities);
        return CarMapper.map2Tos(resultSet);
    }

    @Override
    public Set<CarTo> findCarsRentedInGivenPeriod(Date rentalDate, Date returnDate) {
        List<CarEntity> carEntities = carRepository.findCarsRentedInGivenPeriod(rentalDate, returnDate);
        Set<CarEntity> resultSet = new HashSet<>(carEntities);
        return CarMapper.map2Tos(resultSet);
    }
}
