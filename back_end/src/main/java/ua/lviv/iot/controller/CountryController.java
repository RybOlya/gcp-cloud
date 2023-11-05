package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Country;
import ua.lviv.iot.dto.CountryDto;
import ua.lviv.iot.dto.assembler.CountryDtoAssembler;
import ua.lviv.iot.service.impl.CountryServiceImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/api/counties")
public class CountryController {
    @Autowired
    private CountryServiceImpl countryService;
    @Autowired
    private CountryDtoAssembler countryDtoAssembler;

    @GetMapping(value = "/{countryId}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Integer countryId) {
        Country country = countryService.findById(countryId);
        CountryDto countryDto = countryDtoAssembler.toModel(country);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CountryDto>> getAllCountries() {
        List<Country> countries = countryService.findAll();
        CollectionModel<CountryDto> countryDtos = countryDtoAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(countryDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CountryDto> addCountry(@RequestBody Country country) {
        Country newCountry = countryService.create(country);
        CountryDto countryDto = countryDtoAssembler.toModel(newCountry);
        return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody Country uCountry, @PathVariable Integer countryId) {
        countryService.update(countryId, uCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryId) {
        countryService.delete(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}