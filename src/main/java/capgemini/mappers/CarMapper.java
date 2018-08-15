package capgemini.mappers;

import capgemini.dto.CarTo;
import capgemini.entities.CarEntity;

import java.util.List;
import java.util.stream.Collectors;


public class CarMapper {

    public static CarTo toCarTo(CarEntity carEntity) {
        if (carEntity == null)
            return null;

        return new CarTo.CarToBuilder()
				.withId(carEntity.getId())
                .withBrand(carEntity.getBrand())
                .withModel(carEntity.getModel())
                .withColor(carEntity.getColor())
                .withProductionYear(carEntity.getProductionYear())
                .withEngineCapacity(carEntity.getEngineCapacity())
                .withHorsePower(carEntity.getHorsePower())
                .withMileage(carEntity.getMileage())
                .withCarType(carEntity.getCarType())
                .build();
    }

	public static CarEntity toCarEntity(CarTo carTo) {
		if (carTo == null)
			return null;

		CarEntity carEntity = new CarEntity();
		carEntity.setId(carTo.getId());
		carEntity.setBrand(carTo.getBrand());
		carEntity.setModel(carTo.getModel());
		carEntity.setColor(carTo.getColor());
		carEntity.setProductionYear(carTo.getProductionYear());
		carEntity.setEngineCapacity(carTo.getEngineCapacity());
		carEntity.setHorsePower(carTo.getHorsePower());
		carEntity.setMileage(carTo.getMileage());
		carEntity.setCarType(carTo.getCarType());

		return carEntity;
	}

	public static List<CarTo> map2Tos(List<CarEntity> carEntities) {
		return carEntities.stream().map(CarMapper::toCarTo).collect(Collectors.toList());
	}

	public static List<CarEntity> map2Entities(List<CarTo> carTos) {
		return carTos.stream().map(CarMapper::toCarEntity).collect(Collectors.toList());
	}

}
