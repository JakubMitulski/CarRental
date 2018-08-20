package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTo {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Long addressId;
    private Long positionId;
    private Long departmentId;
    private Set<Long> carIds = new HashSet<>();

    public static EmployeeToBuilder builder() {
        return new EmployeeToBuilder();
    }

    public static class EmployeeToBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private Long addressId;
        private Long positionId;
        private Long departmentId;
        private Set<Long> carIds;

        public EmployeeToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeToBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeToBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeToBuilder withBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public EmployeeToBuilder withAddressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public EmployeeToBuilder withPositionId(Long positionId) {
            this.positionId = positionId;
            return this;
        }

        public EmployeeToBuilder withDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public EmployeeToBuilder withCarIds(Set<Long> carIds) {
            this.carIds = carIds;
            return this;
        }

        public EmployeeTo build() {
            checkBeforeBuild(firstName, lastName, birthDate, addressId, positionId, departmentId, carIds);
            return new EmployeeTo(id, firstName, lastName, birthDate, addressId, positionId, departmentId, carIds);
        }

        private void checkBeforeBuild(String firstName, String lastName, Date birthDate, Long addressId,
                                      Long positionId, Long departmentId, Set<Long> carIds) {
            if (firstName == null || firstName.isEmpty() ||
                    lastName == null || lastName.isEmpty() ||
                    birthDate == null ||
                    addressId == null ||
                    positionId == null ||
                    departmentId == null ||
                    carIds == null) {
                throw new RuntimeException("Incorrect employee_to be created");
            }
        }
    }
}
