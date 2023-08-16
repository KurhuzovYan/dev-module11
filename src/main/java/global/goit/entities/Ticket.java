package global.goit.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id", referencedColumnName = "planet_id", nullable = false)
    private Planet fromPlanetId;

    @ManyToOne
    @JoinColumn(name = "to_planet_id", referencedColumnName = "planet_id", nullable = false)
    private Planet toPlanetId;

    public Ticket() {
    }

    public Ticket(Timestamp createdAt, Client client, Planet fromPlanetId, Planet toPlanetId) {
        this.createdAt = createdAt;
        this.client = client;
        this.fromPlanetId = fromPlanetId;
        this.toPlanetId = toPlanetId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Planet getFromPlanetId() {
        return fromPlanetId;
    }

    public void setFromPlanetId(Planet fromPlanetId) {
        this.fromPlanetId = fromPlanetId;
    }

    public Planet getToPlanetId() {
        return toPlanetId;
    }

    public void setToPlanetId(Planet toPlanetId) {
        this.toPlanetId = toPlanetId;
    }

    @Override
    public String toString() {
        return "Ticket{createdAt=" + createdAt +
                ", client=" + client +
                ", fromPlanetId=" + fromPlanetId +
                ", toPlanetId=" + toPlanetId +
                '}';
    }
}

