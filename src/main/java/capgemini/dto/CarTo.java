package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarTo {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private Year productionYear;
    private Integer engineCapacity;
    private Integer horsePower;
    private Integer mileage;
    private String carType;

    public static CarToBuilder builder() {
        return new CarToBuilder();
    }

    public static class CarToBuilder {

        private Long id;
        private String brand;
        private String model;
        private String color;
        private Year productionYear;
        private Integer engineCapacity;
        private Integer horsePower;
        private Integer mileage;
        private String carType;

        public CarToBuilder() {
            super();
        }

        public CarToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarToBuilder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarToBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public CarToBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public CarToBuilder withProductionYear(Year productionYear) {
            this.productionYear = productionYear;
            return this;
        }

        public CarToBuilder withEngineCapacity(Integer engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public CarToBuilder withHorsePower(Integer horsePower) {
            this.horsePower = horsePower;
            return this;
        }

        public CarToBuilder withMileage(Integer mileage) {
            this.mileage = mileage;
            return this;
        }

        public CarToBuilder withCarType(String carType) {
            this.carType = carType;
            return this;
        }

        public CarTo build() {
            checkBeforeBuild(brand, model, color, productionYear, engineCapacity,
                    horsePower, mileage, carType);
            return new CarTo(id, brand, model, color, productionYear, engineCapacity,
                    horsePower, mileage, carType);
        }

        private void checkBeforeBuild(String brand, String model, String color, Year productionYear,
                                      Integer engineCapacity, Integer horsePower, Integer mileage,
                                      String carType) {
            if (brand == null || brand.isEmpty() ||
                    model == null || model.isEmpty() ||
                    color == null || color.isEmpty() ||
                    productionYear == null ||
                    horsePower == null ||
                    mileage == null ||
                    carType == null || carType.isEmpty()) {
                throw new RuntimeException("Incorrect car_to be created");
            }
        }
    }
}
