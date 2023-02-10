package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.config.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "address_id_generator")
    @SequenceGenerator(name = "address_id_generator",
    sequenceName = "address_seq",
    allocationSize = 1)
    private Long id;
    @Column(name = "region_name")
   private String regionName;
   private String street;
   @Column(name = "home_number")
   private int homeNumber;
   @ManyToOne
   @JoinColumn(name = "country_id")
   private Country country;
   @OneToOne(cascade = {CascadeType.PERSIST,
   CascadeType.MERGE,
   CascadeType.REFRESH,
   CascadeType.DETACH})
   private Programmer programmer;


    public Address(String regionName, String street, int homeNumber) {
        this.regionName = regionName;
        this.street = street;
        this.homeNumber = homeNumber;
    }
}
