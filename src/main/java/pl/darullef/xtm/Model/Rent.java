package pl.darullef.xtm.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue
    private UUID rent_id;

    @Column(name = "rent_start")
    private Date start;

    @Column(name = "rent_end")
    private Date end;

    @OneToOne(targetEntity = Client.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @OneToOne(targetEntity = Car.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;

    public Rent() {
    }

    public Rent(UUID rent_id, @NotBlank Date start, @NotBlank Date end, Client client, Car car) {
        this.rent_id = rent_id;
        this.start = start;
        this.end = end;
        this.client = client;
        this.car = car;
    }

    public UUID getRent_id() {
        return rent_id;
    }

    public void setRent_id(UUID rent_id) {
        this.rent_id = rent_id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
