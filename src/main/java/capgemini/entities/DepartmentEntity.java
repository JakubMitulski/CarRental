package capgemini.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENTS")
public class DepartmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private Long phone;

    @OneToOne
    private AddressEntity addressEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeEntity employeeEntity;

    @OneToMany(targetEntity = HistoryEntity.class, mappedBy = "rentalDepartmentEntity", cascade = CascadeType.ALL)
    private Set<DepartmentEntity> rentalDepartments = new HashSet<>();

    @OneToMany(targetEntity = HistoryEntity.class, mappedBy = "returnDepartmentEntity", cascade = CascadeType.ALL)
    private Set<DepartmentEntity> returnDepartments = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public Set<DepartmentEntity> getRentalDepartments() {
        return rentalDepartments;
    }

    public void setRentalDepartments(Set<DepartmentEntity> rentalDepartments) {
        this.rentalDepartments = rentalDepartments;
    }

    public Set<DepartmentEntity> getReturnDepartments() {
        return returnDepartments;
    }

    public void setReturnDepartments(Set<DepartmentEntity> returnDepartments) {
        this.returnDepartments = returnDepartments;
    }
}
