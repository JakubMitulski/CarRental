package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaQueryEmployeeTo {

    private Long carId;
    private String position;
    private String department;

    public static CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder builder() {
        return new CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder();
    }

    public static class CriteriaQueryEmployeeToBuilder {
        private Long carId;
        private String position;
        private String department;

        public CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder withCarId(Long carId) {
            this.carId = carId;
            return this;
        }

        public CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder withPositionName(String position) {
            this.position = position;
            return this;
        }

        public CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder withDepartmentName(String department) {
            this.department = department;
            return this;
        }

        public CriteriaQueryEmployeeTo build() {
            return new CriteriaQueryEmployeeTo(carId, position, department);
        }
    }
}
