package capgemini.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressTo {

    private Long id;
    private String city;
    private String street;
    private String postcode;

    public AddressTo() {
    }

    public AddressTo(Long id, String city, String street, String postcode) {
        super();
        this.id = id;
        this.city = city;
        this.street = street;
        this.postcode = postcode;
    }

    public static AddressToBuilder builder() {
        return new AddressToBuilder();
    }

    public static class AddressToBuilder {

        private Long id;
        private String city;
        private String street;
        private String postcode;

        public AddressToBuilder() {
            super();
        }

        public AddressToBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AddressToBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressToBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressToBuilder withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public AddressTo build() {
            checkBeforeBuild(city, street, postcode);
            return new AddressTo(id, city, street, postcode);
        }

        private void checkBeforeBuild(String city, String street, String postcode) {
            if (city == null || city.isEmpty() ||
                    street == null || street.isEmpty() ||
                    postcode == null || postcode.isEmpty()) {
                throw new RuntimeException("Incorrect address_to be created");
            }
        }
    }
}
