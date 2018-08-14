package capgemini.service.impl;

import capgemini.dao.CarDao;
import capgemini.dto.CarTo;
import capgemini.entities.CarEntity;
import capgemini.mappers.CarMapper;
import capgemini.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carRepository;

    @Override
    public CarTo findCarById(Long id) {
        CarEntity carEntity = carRepository.findOne(id);
        return CarMapper.toCarTo(carEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public CarTo addNewCar(CarTo carTo) {
        CarEntity carEntity = carRepository.save(CarMapper.toCarEntity(carTo));
        return CarMapper.toCarTo(carEntity);
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.delete(carId);
    }

    @Override
    public CarTo updateCar(CarTo carTo) {
        CarEntity carEntity = carRepository.update(CarMapper.toCarEntity(carTo));
        return CarMapper.toCarTo(carEntity);
    }

    @Override
    public List<CarTo> findCarsByEmployee(Long employeeId) {
        List<CarEntity> cars = carRepository.findCarsByEmployee(employeeId);
        return CarMapper.map2Tos(cars);
    }
}
