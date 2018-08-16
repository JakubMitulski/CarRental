package capgemini.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "ADDRESSES")
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 20)
    private String city;
    @Column(nullable = false, length = 20)
    private String street;
    @Column(nullable = false, length = 10)
    private String postcode;
}
