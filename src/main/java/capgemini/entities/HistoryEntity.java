package capgemini.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date rentalDate;
    @Column(nullable = false)
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private CarEntity carEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentEntity rentalDepartmentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentEntity returnDepartmentEntity;
}
