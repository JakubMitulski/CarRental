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
    private Long positionId;
    private Long departmentId;

    public static CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder builder() {
        return new CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder();
    }

    public static class CriteriaQueryEmployeeToBuilder {
        private Long carId;
        private Long positionId;
        private Long departmentId;

        public CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder withCarId(Long carId) {
            this.carId = carId;
            return this;
        }

        public CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder withPositionId(Long positionId) {
            this.positionId = positionId;
            return this;
        }

        public CriteriaQueryEmployeeTo.CriteriaQueryEmployeeToBuilder withDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public CriteriaQueryEmployeeTo build() {
            return new CriteriaQueryEmployeeTo(carId, positionId, departmentId);
        }
    }
}
