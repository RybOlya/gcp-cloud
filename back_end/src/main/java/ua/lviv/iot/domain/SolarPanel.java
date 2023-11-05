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
@Table(name = "solar_panel")
public class SolarPanel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ip_address", nullable = false)
    private IpAddress ipAddress;

    @Column(name = "model", nullable = false, length = 45)
    private String model;

    @Column(name = "type", nullable = false, length = 35)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solar_system_id", nullable = false)
    private SolarSystem solarSystem;

    @Column(name = "current_angle", nullable = false)
    private Float currentAngle;

}