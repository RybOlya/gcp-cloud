package ua.lviv.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Client;
import ua.lviv.iot.domain.SolarSystem;
import ua.lviv.iot.dto.ClientDto;
import ua.lviv.iot.dto.SolarSystemDto;
import ua.lviv.iot.dto.assembler.SolarSystemDtoAssembler;
import ua.lviv.iot.service.SolarSystemService;
import ua.lviv.iot.service.impl.StoredProceduresService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/solarSystems")
public class SolarSystemController {
    @Autowired
    private SolarSystemService solarSystemService;
    @Autowired
    private SolarSystemDtoAssembler solarSystemDtoAssembler;
    @Autowired
    private StoredProceduresService storedProceduresService;
    @GetMapping(value = "/{solarSystemId}")
    public ResponseEntity<SolarSystemDto> getSolarSystem(@PathVariable Integer solarSystemId) {
        SolarSystem solarSystem = solarSystemService.findById(solarSystemId);
        SolarSystemDto solarSystemDto = solarSystemDtoAssembler.toModel(solarSystem);
        return new ResponseEntity<>(solarSystemDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<SolarSystemDto>> getAllSolarSystems() {
        List<SolarSystem> solarSystems = solarSystemService.findAll();
        CollectionModel<SolarSystemDto> solarSystemDtos = solarSystemDtoAssembler.toCollectionModel(solarSystems);
        return new ResponseEntity<>(solarSystemDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/energySold/{energySold}")
    public ResponseEntity<CollectionModel<SolarSystemDto>> getSolarSystemsByEnergySold(@PathVariable Integer energySold) {
        List<SolarSystem> solarSystems = solarSystemService.findByEnergySold(energySold);
        CollectionModel<SolarSystemDto> solarSystemDtos = solarSystemDtoAssembler.toCollectionModel(solarSystems);
        return new ResponseEntity<>(solarSystemDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/{cityId}")
    public ResponseEntity<SolarSystemDto> addSolarSystemWithCity(@RequestBody SolarSystem solarSystem,
                                                         @PathVariable Integer cityId) {
        SolarSystem newSolarSystem = solarSystemService.create(solarSystem, cityId);
        SolarSystemDto solarSystemDto = solarSystemDtoAssembler.toModel(newSolarSystem);
        return new ResponseEntity<>(solarSystemDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{solarSystemId}/{cityId}")
    public ResponseEntity<?> updateSolarSystem(@RequestBody SolarSystem uSolarSystem,
                                              @PathVariable Integer solarSystemId,@PathVariable Integer cityId) {

        solarSystemService.update(solarSystemId, uSolarSystem,cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{solarSystemId}")
    public ResponseEntity<?> deleteSolarSystem(@PathVariable Integer solarSystemId) {
        solarSystemService.delete(solarSystemId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/max/energy-sold")
    public ResponseEntity<String> countMaxEnergySold() {
        return new ResponseEntity<>(solarSystemService.getMaxEnergySold(), HttpStatus.OK);
    }

}
