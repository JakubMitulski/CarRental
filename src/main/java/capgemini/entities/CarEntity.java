package capgemini.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CARS")
public class CarEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String brand;
    @Column(nullable = false, length = 50)
    private String model;
    @Column(nullable = false, length = 50)
    private String color;
    @Column(nullable = false)
    private Date productonYear;
    @Column(nullable = false)
    private Integer engineCapacity;
    @Column(nullable = false)
    private Integer horsePower;
    @Column(nullable = false)
    private Integer mileage;
    @Column(nullable = false, length = 50)
    private String carType;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmployeeEntity employeeEntity;

    @OneToMany(targetEntity = HistoryEntity.class, mappedBy = "carEntity", cascade = CascadeType.ALL)
    private Set<HistoryEntity> rentedCars = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getProductonYear() {
        return productonYear;
    }

    public void setProductonYear(Date productonYear) {
        this.productonYear = productonYear;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public Set<HistoryEntity> getRentedCars() {
        return rentedCars;
    }

    public void setRentedCars(Set<HistoryEntity> rentedCars) {
        this.rentedCars = rentedCars;
    }
}
