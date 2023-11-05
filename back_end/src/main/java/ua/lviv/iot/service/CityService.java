package ua.lviv.iot.service;

import org.springframework.data.jpa.repository.query.Procedure;
import ua.lviv.iot.domain.City;

public interface CityService extends GeneralService<City, Integer>{
    void createTablesByCityNames();
}
