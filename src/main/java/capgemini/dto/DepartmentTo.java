package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentTo {

    private Long id;
    private String name;
    private Long phone;
    private AddressTo addressTo;

    public static DepartmentToBuilder builder() {
        return new DepartmentToBuilder();
    }

    public static class DepartmentToBuilder {

        private Long id;
        private String name;
        private Long phone;
        private AddressTo addressTo;

        public DepartmentToBuilder() {
            super();
        }

        public DepartmentToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public DepartmentToBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DepartmentToBuilder withPhone(Long phone) {
            this.phone = phone;
            return this;
        }

        public DepartmentToBuilder withAddressTo(AddressTo addressTo) {
            this.addressTo = addressTo;
            return this;
        }

        public DepartmentTo build() {
            checkBeforeBuild(name, phone, addressTo);
            return new DepartmentTo(id, name, phone, addressTo);
        }

        private void checkBeforeBuild(String name, Long phone, AddressTo addressTo) {
            if (name == null || name.isEmpty() || phone == null || addressTo == null) {
                throw new RuntimeException("Incorrect department_to be created");
            }
        }
    }
}
