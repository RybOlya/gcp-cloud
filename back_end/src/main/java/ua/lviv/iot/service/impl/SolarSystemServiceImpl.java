package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.City;
import ua.lviv.iot.domain.IpAddress;
import ua.lviv.iot.domain.SolarBattery;
import ua.lviv.iot.domain.SolarSystem;
import ua.lviv.iot.exception.*;
import ua.lviv.iot.repository.IpAddressRepository;
import ua.lviv.iot.repository.SolarSystemRepository;
import ua.lviv.iot.repository.ClientRepository;
import ua.lviv.iot.repository.CityRepository;
import ua.lviv.iot.service.SolarSystemService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class SolarSystemServiceImpl implements SolarSystemService {
    @Autowired
    SolarSystemRepository solarSystemRepository;

    @Autowired
    IpAddressRepository ipAddressRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    ClientRepository clientRepository;

    public SolarSystem findById(Integer id) {
        return solarSystemRepository.findById(id)
                .orElseThrow(() -> new SolarSystemNotFoundException(id));
    }

    public List<SolarSystem> findAll() {
        return solarSystemRepository.findAll();
    }

    @Transactional
    public SolarSystem create(SolarSystem solarSystem) {
        solarSystemRepository.save(solarSystem);
        return solarSystem;
    }

    @Transactional
    public SolarSystem create(SolarSystem solarSystem, Integer cityId) {

        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
        solarSystem.setCity(city);
        solarSystemRepository.save(solarSystem);
        return solarSystem;
    }

    @Transactional
    public void update(Integer solarSystemId, SolarSystem uSolarSystem) {

        SolarSystem solarSystem = solarSystemRepository.findById(solarSystemId)
                .orElseThrow(() -> new SolarBatteryNotFoundException(solarSystemId));
        //update
        solarSystem.setApartment(uSolarSystem.getApartment());
        solarSystem.setEnergySold(uSolarSystem.getEnergySold());
        solarSystem.setStreet(uSolarSystem.getStreet());
        solarSystem.setFeedInTariff(uSolarSystem.getFeedInTariff());
        solarSystemRepository.save(solarSystem);

    }


    @Transactional
    public void update(Integer solarSystemId, SolarSystem uSolarSystem,  Integer cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
        SolarSystem solarSystem = solarSystemRepository.findById(solarSystemId)
                .orElseThrow(() -> new SolarSystemNotFoundException(solarSystemId));
        //update
        solarSystem.setApartment(uSolarSystem.getApartment());
        solarSystem.setCity(city);
        solarSystem.setEnergySold(uSolarSystem.getEnergySold());
        solarSystem.setStreet(uSolarSystem.getStreet());
        solarSystem.setFeedInTariff(uSolarSystem.getFeedInTariff());
        solarSystemRepository.save(solarSystem);
    }

    public String getMaxEnergySold() {
        return ("Max energy sold: " + solarSystemRepository.getMaxEnergySold());
    }
    public List<SolarSystem> findByEnergySold(Integer energySold) {
        return solarSystemRepository.findByEnergySold(energySold);
    }

    @Transactional
    public void delete(Integer id) {
        SolarSystem solarSystem = solarSystemRepository.findById(id)
                .orElseThrow(() -> new SolarSystemNotFoundException(id));
        if (!solarSystem.getSolarBatteries().isEmpty()&&!solarSystem.getSolarPanels().isEmpty()) throw new SolarExistForSolarSystemException(id);
        solarSystemRepository.delete(solarSystem);
    }
}
