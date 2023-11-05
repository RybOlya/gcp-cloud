package ua.lviv.iot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "solar_system")
public class SolarSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "feed_in_tariff", nullable = false)
    private Double feedInTariff;

    @Column(name = "energy_sold", nullable = false)
    private Integer energySold;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "street", length = 30)
    private String street;
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    @Column(name = "apartment", length = 10)
    private String apartment;
    @ManyToMany(mappedBy = "solarSystems")
    private Set<Client> clients;
    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
    @OneToMany(mappedBy="solarSystem")
    private List<SolarPanel> solarPanels;
    public List<SolarPanel> getSolarPanels() {
        return solarPanels;
    }
    public void setSolarPanels(List<SolarPanel> solarPanels) {
        this.solarPanels = solarPanels;
    }
    @OneToMany(mappedBy="solarSystem")
    private List<SolarBattery> solarBatteries;
    public List<SolarBattery> getSolarBatteries() {
        return solarBatteries;
    }
    public void setSolarBatteries(List<SolarBattery> solarBatteries) {
        this.solarBatteries = solarBatteries;
    }

}