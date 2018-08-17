package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryTo implements Serializable {

    private Long id;
    private Integer price;
    private Date rentalDate;
    private Date returnDate;
    private Long carId;
    private Long customerId;
    private Long rentalDepartmentId;
    private Long returnDepartmentId;

    public static HistoryToBuilder builder() {
        return new HistoryToBuilder();
    }

    public static class HistoryToBuilder {

        private Long id;
        private Integer price;
        private Date rentalDate;
        private Date returnDate;
        private Long carId;
        private Long customerId;
        private Long rentalDepartmentId;
        private Long returnDepartmentId;

        public HistoryToBuilder() {
            super();
        }

        public HistoryToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public HistoryToBuilder withPrice(Integer price) {
            this.price = price;
            return this;
        }

        public HistoryToBuilder withRentalDate(Date rentalDate) {
            this.rentalDate = rentalDate;
            return this;
        }

        public HistoryToBuilder withReturnDate(Date returnDate) {
            this.returnDate = returnDate;
            return this;
        }

        public HistoryToBuilder withCarId(Long carId) {
            this.carId = carId;
            return this;
        }

        public HistoryToBuilder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public HistoryToBuilder withRentalDepartmentId(Long rentalDepartmentId) {
            this.rentalDepartmentId = rentalDepartmentId;
            return this;
        }

        public HistoryToBuilder withReturnDepartmentId(Long returnDepartmentId) {
            this.returnDepartmentId = returnDepartmentId;
            return this;
        }

        public HistoryTo build() {
            checkBeforeBuild(price, rentalDate, returnDate, carId, customerId,
                    rentalDepartmentId, returnDepartmentId);
            return new HistoryTo(id, price, rentalDate, returnDate, carId, customerId,
                    rentalDepartmentId, returnDepartmentId);
        }

        private void checkBeforeBuild(Integer price, Date rentalDate, Date returnDate, Long carId,
                                      Long customerId, Long rentalDepartmentId, Long returnDepartmentId) {
            if (price == null ||
                    rentalDate == null ||
                    returnDate == null ||
                    carId == null ||
                    customerId == null ||
                    rentalDepartmentId == null ||
                    returnDepartmentId == null) {
                throw new RuntimeException("Incorrect history_to be created");
            }
        }
    }
}
