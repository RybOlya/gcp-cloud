package ua.lviv.iot.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class IpAddress {
    @Id
    @Column(name = "ip_address", length = 50)
    private String ipAddress;
    @OneToMany(mappedBy="ipAddress")
    private List<SolarPanel> solarPanels;
    public List<SolarPanel> getSolarPanels() {
        return solarPanels;
    }
    public void setSolarPanels(List<SolarPanel> solarPanels) {
        this.solarPanels = solarPanels;
    }
    @OneToMany(mappedBy="ipAddress")
    private List<SolarBattery> solarBatteries;
    public List<SolarBattery> getSolarBatteries() {
        return solarBatteries;
    }
    public void setSolarBatteries(List<SolarBattery> solarBatteries) {
        this.solarBatteries = solarBatteries;
    }
}