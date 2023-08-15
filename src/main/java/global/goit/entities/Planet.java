package global.goit.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @Column(name = "planet_id")
    private String id;

    @Column
    private String name;

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Planet() {
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}