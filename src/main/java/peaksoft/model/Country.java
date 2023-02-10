package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "country_id_generator")
    @SequenceGenerator(name = "country_id_generator",
    sequenceName = "country_seq",
    allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private peaksoft.config.enums.Country country;
    private String description;
    @OneToMany(cascade = {CascadeType.PERSIST,
    CascadeType.MERGE,
    CascadeType.REFRESH,
    CascadeType.DETACH})
    private List<Address>addresses;


    public Country(peaksoft.config.enums.Country country, String description) {
        this.country = country;
        this.description = description;
    }
}
