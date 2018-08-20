package capgemini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTo {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private Long cardNumber;
    private Long phone;
    private Long addressId;

    public static CustomerToBuilder builder() {
        return new CustomerToBuilder();
    }

    public static class CustomerToBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private Date birthDate;
        private Long cardNumber;
        private Long phone;
        private Long addressId;

        public CustomerToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CustomerToBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerToBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerToBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerToBuilder withBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public CustomerToBuilder withCardNumber(Long cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CustomerToBuilder withPhone(Long phone) {
            this.phone = phone;
            return this;
        }

        public CustomerToBuilder withAddressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public CustomerTo build() {
            checkBeforeBuild(firstName, lastName, email, birthDate, cardNumber, phone, addressId);
            return new CustomerTo(id, firstName, lastName, email, birthDate, cardNumber, phone, addressId);
        }

        private void checkBeforeBuild(String firstName, String lastName, String email, Date birthDate, Long cardNumber, Long phone, Long addressId) {
            if (firstName == null || firstName.isEmpty() ||
                    lastName == null || lastName.isEmpty() ||
                    email == null || email.isEmpty() ||
                    birthDate == null ||
                    cardNumber == null ||
                    phone == null ||
                    addressId == null
            ) {
                throw new RuntimeException("Incorrect customer_to be created");
            }
        }
    }
}
