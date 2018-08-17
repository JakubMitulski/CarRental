package capgemini.mappers;

import capgemini.dao.CarDao;
import capgemini.dao.CustomerDao;
import capgemini.dao.DepartmentDao;
import capgemini.dto.HistoryTo;
import capgemini.entities.HistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryMapper {

    @Autowired
    private static CarDao carRepository;

    @Autowired
    private static CustomerDao customerRepository;

    @Autowired
    private static DepartmentDao departmentRepository;


    public static HistoryTo toHistoryTo(HistoryEntity historyEntity) {
        if (historyEntity == null)
            return null;

        return new HistoryTo.HistoryToBuilder()
                .withId(historyEntity.getId())
                .withPrice(historyEntity.getPrice())
                .withRentalDate(historyEntity.getRentalDate())
                .withReturnDate(historyEntity.getReturnDate())
                .withCarId(historyEntity.getCarEntity().getId())
                .withCustomerId(historyEntity.getCustomerEntity().getId())
                .withRentalDepartmentId(historyEntity.getRentalDepartmentEntity().getId())
                .withReturnDepartmentId(historyEntity.getReturnDepartmentEntity().getId())
                .build();
    }

    public static HistoryEntity toHistoryEntity(HistoryTo historyTo) {
        if (historyTo == null)
            return null;

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(historyTo.getId());
        historyEntity.setPrice(historyTo.getPrice());
        historyEntity.setRentalDate(historyTo.getRentalDate());
        historyEntity.setReturnDate(historyTo.getReturnDate());

        return historyEntity;
    }

    public static List<HistoryTo> map2Tos(List<HistoryEntity> historyEntities) {
        return historyEntities.stream().map(HistoryMapper::toHistoryTo).collect(Collectors.toList());
    }

    public static List<HistoryEntity> map2Entities(List<HistoryTo> historyTos) {
        return historyTos.stream().map(HistoryMapper::toHistoryEntity).collect(Collectors.toList());
    }

}
