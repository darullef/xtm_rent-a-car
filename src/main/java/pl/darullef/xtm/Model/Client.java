package pl.darullef.xtm.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private UUID client_id;

    @NotBlank
    @Column(name = "name")
    private String name;

    public Client() {}

    public Client(UUID client_id, @NotBlank String name) {
        this.client_id = client_id;
        this.name = name;
    }

    public UUID getClient_id() {
        return client_id;
    }

    public void setClient_id(UUID client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
