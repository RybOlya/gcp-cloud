package ua.lviv.iot.service;

import org.springframework.data.jpa.repository.query.Procedure;
import ua.lviv.iot.domain.Client;
import ua.lviv.iot.domain.SolarSystem;

import java.util.List;

public interface SolarSystemService  extends GeneralService<SolarSystem, Integer>{
    SolarSystem create(SolarSystem entity, Integer cityId);
    void update(Integer solarSystem, SolarSystem uSolarSystem, Integer cityId);
    List<SolarSystem> findByEnergySold(Integer energySold);
    String getMaxEnergySold();
}
