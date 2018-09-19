package capgemini.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMERS")
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private Long cardNumber;
    @Column(nullable = false)
    private Long phone;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity addressEntity;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.REMOVE)
    private Collection<HistoryEntity> historyEntities = new HashSet<>();

}
