package capgemini.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false)
    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID")
    private PositionEntity positionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private DepartmentEntity departmentEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RESPONSIBILITIES",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "CAR_ID", nullable = false, updatable = false)}
    )
    private Set<CarEntity> cars = new HashSet<>();

}
