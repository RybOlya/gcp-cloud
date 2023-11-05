package ua.lviv.iot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "solar_battery")
public class SolarBattery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "model", nullable = false, length = 45)
    private String model;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ip_address", nullable = false)
    private IpAddress ipAddress;


    @Column(name = "capacity", nullable = false)
    private Double capacity;

    @Column(name = "operating_temperature", nullable = false)
    private Integer operatingTemperature;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solar_system_id", nullable = false)
    private SolarSystem solarSystem;

}