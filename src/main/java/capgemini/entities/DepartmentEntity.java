package capgemini.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "DEPARTMENTS")
public class DepartmentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long phone;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;

    @OneToMany(mappedBy = "rentalDepartmentEntity", cascade = CascadeType.REMOVE)
    private Collection<HistoryEntity> rentalHistoryEntities = new HashSet<>();

    @OneToMany(mappedBy = "returnDepartmentEntity", cascade = CascadeType.REMOVE)
    private Collection<HistoryEntity> returnHistoryEntities = new HashSet<>();

    public void addRentalHistoryEntry(HistoryEntity historyEntity){
        this.rentalHistoryEntities.add(historyEntity);
    }

    public void addReturnHistoryEntry(HistoryEntity historyEntity){
        this.returnHistoryEntities.add(historyEntity);
    }
}
