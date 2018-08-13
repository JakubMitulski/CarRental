package capgemini.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne
    private AddressEntity address;

    @OneToMany(targetEntity = PositionEntity.class, mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private Set<PositionEntity> positions = new HashSet<>();

    @OneToMany(targetEntity = DepartmentEntity.class, mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    private Set<DepartmentEntity> departments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "RESPONSIBILITIES",
            joinColumns = {@JoinColumn(name = "EMPOYEE_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "CAR_ID", nullable = false, updatable = false)}
    )
    private Set<CarEntity> cars = new HashSet<>();


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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public AddressEntity getAddres() {
        return address;
    }

    public void setAddres(AddressEntity addres) {
        this.address = addres;
    }

    public Set<PositionEntity> getPositions() {
        return positions;
    }

    public void setPositions(Set<PositionEntity> positions) {
        this.positions = positions;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }
}
