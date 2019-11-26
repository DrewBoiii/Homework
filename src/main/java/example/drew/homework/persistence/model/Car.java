package example.drew.homework.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private Long kilometers;

    @Column
    private String description;

    @Column
    private LocalDate build;

    @ManyToOne
    private User person;

    @Column
    private LocalDate createdAt;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Favorites> favorites;

    @PrePersist
    void createdAt(){
        this.createdAt = LocalDate.now();
    }
}
