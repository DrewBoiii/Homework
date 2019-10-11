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
public class Car implements Comparable<Car> {

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

    @Column
    @ManyToOne(targetEntity = Car.class)
    private User user;

    @Column
    private Date createdAT;

    @PrePersist
    void createdAt(){
        this.createdAT = new Date();
    }

    @Override
    public int compareTo(Car o) {
        return Long.compare(this.createdAT.getTime(), o.createdAT.getTime());
    }

}
