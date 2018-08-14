package capgemini.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "HISTORY")
public class HistoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Timestamp rentalDate;
    @Column(nullable = false)
    private Timestamp returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cars_id", nullable = false)
    private CarEntity carEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customers_id", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalDepartments_id", nullable = false)
    private DepartmentEntity rentalDepartmentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "returnDepartments_id", nullable = false)
    private DepartmentEntity returnDepartmentEntity;
}
