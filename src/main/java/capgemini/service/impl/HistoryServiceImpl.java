package capgemini.service.impl;

import capgemini.dao.CarDao;
import capgemini.dao.CustomerDao;
import capgemini.dao.DepartmentDao;
import capgemini.dao.HistoryDao;
import capgemini.dto.HistoryTo;
import capgemini.entities.CarEntity;
import capgemini.entities.CustomerEntity;
import capgemini.entities.DepartmentEntity;
import capgemini.entities.HistoryEntity;
import capgemini.mappers.HistoryMapper;
import capgemini.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyRepository;

    @Autowired
    private CarDao carRepository;

    @Autowired
    private CustomerDao customerRepository;

    @Autowired
    private DepartmentDao departmentrepository;

    @Override
    public HistoryTo findHistoryEntryById(Long id) {
        return HistoryMapper.toHistoryTo(historyRepository.findOne(id));
    }

    @Override
    public HistoryTo addNewHistoryEntry(HistoryTo historyTo) {
        HistoryEntity historyEntity = HistoryMapper.toHistoryEntity(historyTo);

        CarEntity carEntity = carRepository.findOne(historyTo.getCarId());
        historyEntity.setCarEntity(carEntity);

        CustomerEntity customerEntity = customerRepository.findOne(historyTo.getCustomerId());
        historyEntity.setCustomerEntity(customerEntity);

        DepartmentEntity rentalDepartmentEntity = departmentrepository.findOne(historyTo.getRentalDepartmentId());
        historyEntity.setRentalDepartmentEntity(rentalDepartmentEntity);

        DepartmentEntity returnDepartmentEntity = departmentrepository.findOne(historyTo.getReturnDepartmentId());
        historyEntity.setReturnDepartmentEntity(returnDepartmentEntity);

        HistoryEntity savedHistoryEntity = historyRepository.save(historyEntity);

        carEntity.addHistoryEntry(savedHistoryEntity);
        return HistoryMapper.toHistoryTo(savedHistoryEntity);
    }

    @Override
    public void deleteHistoryEntry(HistoryTo historyTo) {
        historyRepository.delete(historyTo.getId());
    }
}
