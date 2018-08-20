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
    private Long addressId;

    public static DepartmentToBuilder builder() {
        return new DepartmentToBuilder();
    }

    public static class DepartmentToBuilder {

        private Long id;
        private String name;
        private Long phone;
        private Long addressId;

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

        public DepartmentToBuilder withAddressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public DepartmentTo build() {
            checkBeforeBuild(name, phone, addressId);
            return new DepartmentTo(id, name, phone, addressId);
        }

        private void checkBeforeBuild(String name, Long phone, Long addressId) {
            if (name == null || name.isEmpty() || phone == null || addressId == null) {
                throw new RuntimeException("Incorrect department_to be created");
            }
        }
    }
}
