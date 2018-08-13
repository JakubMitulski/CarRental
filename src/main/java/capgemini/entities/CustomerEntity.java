package capgemini.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMERS")
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private Long cardNumber;
    @Column(nullable = false)
    private Long phone;

    @OneToOne
    private AddressEntity addressEntity;

    @OneToMany(targetEntity = HistoryEntity.class, mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private Set<HistoryEntity> rentalCustomers = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public AddressEntity getAddres() {
        return addressEntity;
    }

    public void setAddres(AddressEntity addres) {
        this.addressEntity = addres;
    }

    public Set<HistoryEntity> getRentalCustomers() {
        return rentalCustomers;
    }

    public void setRentalCustomers(Set<HistoryEntity> rentalCustomers) {
        this.rentalCustomers = rentalCustomers;
    }
}
