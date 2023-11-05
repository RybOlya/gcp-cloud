package ua.lviv.iot.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @OneToMany(mappedBy = "city")
    private List<City> cities;
}
