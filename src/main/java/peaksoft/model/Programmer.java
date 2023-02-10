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
@Table(name = "programmers")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "programmer_id_generator")
    @SequenceGenerator(name = "programmer_id_generator",
    sequenceName = "programmer_seq",
    allocationSize = 1)

    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = {CascadeType.PERSIST,
    CascadeType.MERGE,
    CascadeType.REFRESH,
    CascadeType.DETACH})
    private Address location;
    @ManyToMany(cascade = {CascadeType.PERSIST,
    CascadeType.MERGE,
    CascadeType.REFRESH,
    CascadeType.DETACH})
   private List<Project> projects;


    public Programmer(String fullName, String email, LocalDate dateOfBirth, Status status) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }
}

