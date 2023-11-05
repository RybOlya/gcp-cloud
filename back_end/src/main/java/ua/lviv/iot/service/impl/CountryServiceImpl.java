package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.Country;
import ua.lviv.iot.exception.CountryNotFoundException;
import ua.lviv.iot.repository.CountryRepository;
import ua.lviv.iot.service.GeneralService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements GeneralService<Country, Integer> {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Integer id) {
        return countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        countryRepository.save(country);
        return country;
    }

    @Override
    @Transactional
    public void update(Integer id, Country uCountry) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(uCountry.getName());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));

        countryRepository.delete(country);
    }
    


    public void createTablesByCountryNames() {
        countryRepository.createTablesByCountryNames();
    }
}