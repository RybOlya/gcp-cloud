package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.repository.CountryRepository;
import ua.lviv.iot.repository.SolarSystemRepository;

@Service
public class StoredProceduresService {
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    SolarSystemRepository solarSystemRepository;


}