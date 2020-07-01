package pl.darullef.xtm.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue
    private UUID car_id;

    @NotBlank
    @Column(name = "made")
    private String made;

    @NotBlank
    @Column(name = "model")
    private String model;

    public Car() {
    }

    public Car(UUID car_id, @NotBlank String made, @NotBlank String model) {
        this.car_id = car_id;
        this.made = made;
        this.model = model;
    }

    public UUID getCar_id() {
        return car_id;
    }

    public void setCar_id(UUID car_id) {
        this.car_id = car_id;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
