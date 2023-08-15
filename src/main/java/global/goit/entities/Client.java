package global.goit.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{name= " + name + "}";
    }
}

