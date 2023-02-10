package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "project_id_generator")
    @SequenceGenerator(name = "project_id_generator",
    sequenceName = "project_seq",
    allocationSize = 1)
    private Long id;
    @Column(name = "project_name")
    private String projectName;
    private String description;
    @Column(name = "date_of_start")
    private LocalDate dateOfStart;
    @Column(name = "date_of_finish")
    private LocalDate dateOfFinish;
    private int price;
    @ManyToMany(mappedBy = "projects",
            cascade ={CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH} )
    private List<Programmer> programmers;


    public Project(String projectName, String description, LocalDate dateOfStart, LocalDate dateOfFinish, int price) {
        this.projectName = projectName;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.price = price;
    }
}
