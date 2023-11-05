package ua.lviv.iot.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.hateoas.CollectionModel;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import ua.lviv.iot.domain.City;
        import ua.lviv.iot.dto.CityDto;
        import ua.lviv.iot.dto.assembler.CityDtoAssembler;
        import ua.lviv.iot.service.CityService;

        import java.util.List;

@RestController
@RequestMapping(value = "/api/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityDtoAssembler cityDtoAssembler;

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<CityDto> getCity(@PathVariable Integer cityId) {
        City city = cityService.findById(cityId);
        CityDto cityDto = cityDtoAssembler.toModel(city);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CityDto>> getAllCities() {
        List<City> cities = cityService.findAll();
        CollectionModel<CityDto> cityDtos = cityDtoAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CityDto> addCity(@RequestBody City city) {
        City newCity = cityService.create(city);
        CityDto cityDto = cityDtoAssembler.toModel(newCity);
        return new ResponseEntity<>(cityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> updateCity(@RequestBody City uCity, @PathVariable Integer cityId) {
        cityService.update(cityId, uCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer cityId) {
        cityService.delete(cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/create/tables")
    public void createTablesByCityNames(){
        cityService.createTablesByCityNames();
    }
  }
