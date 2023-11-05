package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.City;
import ua.lviv.iot.exception.CityNotFoundException;
import ua.lviv.iot.exception.ClientsExistForCityException;
import ua.lviv.iot.repository.CityRepository;
import ua.lviv.iot.repository.ClientRepository;
import ua.lviv.iot.service.CityService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    ClientRepository clientRepository;

    public City findById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Transactional
    public City create(City city) {
        cityRepository.save(city);
        return city;
    }

    @Transactional
    public void update(Integer id, City uCity) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        //update
        city.setCity(uCity.getCity());
        cityRepository.save(city);
    }

    @Transactional
    public void delete(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        if (!city.getClients().isEmpty()) throw new ClientsExistForCityException(id);
        cityRepository.delete(city);
    }
    public void createTablesByCityNames() {
        cityRepository.createTablesByCityNames();
    }
}
