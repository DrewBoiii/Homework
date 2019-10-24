package example.drew.homework.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
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
    private Date build;

    @ManyToOne
    private User person;

    @Column
    private Date createdAT;

    @PrePersist
    void createdAt(){
        this.createdAT = new Date();
    }
}
