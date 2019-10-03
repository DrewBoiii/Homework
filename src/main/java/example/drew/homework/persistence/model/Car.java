package example.drew.homework.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Car implements Comparable<Car> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;

    private String model;

    private Long kilometers;

    private Date build;

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
