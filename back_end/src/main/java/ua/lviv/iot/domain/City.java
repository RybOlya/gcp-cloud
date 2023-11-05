package ua.lviv.iot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @OneToMany(mappedBy = "city")
    private List<Client> clients;
    @Column(name = "city", nullable = false, length = 25)
    private String city;
    public List<Client> getClients() {
        return clients;
    }
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}