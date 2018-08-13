package capgemini.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "HISTORY")
public class HistoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Date rentalDate;
    @Column(nullable = false)
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cars_id", nullable = false)
    private CarEntity carEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customers_id", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalDepartments_id", nullable = false)
    private CustomerEntity rentalDepartmentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "returnDepartments_id", nullable = false)
    private CustomerEntity returnDepartmentEntity;


    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public CarEntity getCarEntity() {
        return carEntity;
    }

    public void setCarEntity(CarEntity carEntity) {
        this.carEntity = carEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public CustomerEntity getRentalDepartmentEntity() {
        return rentalDepartmentEntity;
    }

    public void setRentalDepartmentEntity(CustomerEntity rentalDepartmentEntity) {
        this.rentalDepartmentEntity = rentalDepartmentEntity;
    }

    public CustomerEntity getReturnDepartmentEntity() {
        return returnDepartmentEntity;
    }

    public void setReturnDepartmentEntity(CustomerEntity returnDepartmentEntity) {
        this.returnDepartmentEntity = returnDepartmentEntity;
    }
}
