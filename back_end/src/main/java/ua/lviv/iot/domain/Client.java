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
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", length = 45)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToMany
    @JoinTable(name="solar_system_client", catalog="", schema = "solar_system",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "solar_system_id",referencedColumnName = "id",nullable = false))

    private Set<SolarSystem> solarSystems;

    @Column(name = "street", length = 30)
    private String street;

    @Column(name = "apartment", length = 10)
    private String apartment;

   }