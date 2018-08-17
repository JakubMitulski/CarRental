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
    private AddressTo addressTo;
    private PositionTo positionTo;
    private DepartmentTo departmentTo;
    private Set<CarTo> carTos = new HashSet<>();

    public static EmployeeToBuilder builder() {
        return new EmployeeToBuilder();
    }

    public static class EmployeeToBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private AddressTo addressTo;
        private PositionTo positionTo;
        private DepartmentTo departmentTo;
        private Set<CarTo> carTos = new HashSet<>();

        public EmployeeToBuilder() {
            super();
        }

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

        public EmployeeToBuilder withAddressTo(AddressTo addressTo) {
            this.addressTo = addressTo;
            return this;
        }

        public EmployeeToBuilder withPositionTo(PositionTo positionTo) {
            this.positionTo = positionTo;
            return this;
        }

        public EmployeeToBuilder withDepartmentTo(DepartmentTo departmentTo) {
            this.departmentTo = departmentTo;
            return this;
        }

        public EmployeeToBuilder withCarTos(Set<CarTo> carTos) {
            this.carTos = carTos;
            return this;
        }

        public EmployeeTo build() {
            checkBeforeBuild(firstName, lastName, birthDate, addressTo, positionTo, departmentTo, carTos);
            return new EmployeeTo(id, firstName, lastName, birthDate, addressTo, positionTo, departmentTo, carTos);
        }

        private void checkBeforeBuild(String firstName, String lastName, Date birthDate, AddressTo addressTo,
                                      PositionTo positionTo, DepartmentTo departmentTo,
                                      Set<CarTo> carTos) {
            if (firstName == null || firstName.isEmpty() ||
                    lastName == null || lastName.isEmpty() ||
                    birthDate == null ||
                    addressTo == null ||
                    positionTo == null ||
                    carTos == null) {
                throw new RuntimeException("Incorrect employee_to be created");
            }
        }
    }
}
