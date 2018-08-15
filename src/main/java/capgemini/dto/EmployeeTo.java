package capgemini.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class EmployeeTo {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private AddressTo addressTo;
    private Set<PositionTo> positionTos = new HashSet<>();
    private Set<DepartmentTo> departmentTos = new HashSet<>();
    private Set<CarTo> carTos = new HashSet<>();

    public EmployeeTo() {
    }

    public EmployeeTo(Long id, String firstName, String lastName, Date birthDate, AddressTo addressTo,
                      Set<PositionTo> positionTos, Set<DepartmentTo> departmentTos, Set<CarTo> carTos) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.addressTo = addressTo;
        this.positionTos = positionTos;
        this.departmentTos = departmentTos;
        this.carTos = carTos;
    }

    public static EmployeeToBuilder builder() {
        return new EmployeeToBuilder();
    }

    public static class EmployeeToBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private AddressTo addressTo;
        private Set<PositionTo> positionTos = new HashSet<>();
        private Set<DepartmentTo> departmentTos = new HashSet<>();
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

        public EmployeeToBuilder withPositionTos(Set<PositionTo> positionTos) {
            this.positionTos = positionTos;
            return this;
        }

        public EmployeeToBuilder withDepartmentTos(Set<DepartmentTo> departmentTos) {
            this.departmentTos = departmentTos;
            return this;
        }

        public EmployeeToBuilder withCarTos(Set<CarTo> carTos) {
            this.carTos = carTos;
            return this;
        }

        public EmployeeTo build() {
            checkBeforeBuild(firstName, lastName, birthDate, addressTo, positionTos, departmentTos, carTos);
            return new EmployeeTo(id, firstName, lastName, birthDate, addressTo, positionTos, departmentTos, carTos);
        }

        private void checkBeforeBuild(String firstName, String lastName, Date birthDate, AddressTo addressTo,
                                      Set<PositionTo> positionTos, Set<DepartmentTo> departmentTos,
                                      Set<CarTo> carTos) {
            if (firstName == null || firstName.isEmpty() ||
                    lastName == null || lastName.isEmpty() ||
                    birthDate == null ||
                    addressTo == null ||
                    positionTos == null ||
                    departmentTos == null ||
                    carTos == null) {
                throw new RuntimeException("Incorrect employee_to be created");
            }
        }
    }
}
