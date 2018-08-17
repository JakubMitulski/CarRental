package capgemini.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Collection;

@Getter
@Setter
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
    private Year productionYear;
    @Column(nullable = false)
    private Integer engineCapacity;
    @Column(nullable = false)
    private Integer horsePower;
    @Column(nullable = false)
    private Integer mileage;
    @Column(nullable = false, length = 50)
    private String carType;

    @OneToMany(mappedBy = "carEntity", cascade = CascadeType.REMOVE)
    private Collection<HistoryEntity> historyEntities;
}
