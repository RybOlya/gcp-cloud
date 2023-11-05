package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.IpAddress;
import ua.lviv.iot.dto.IpAddressDto;
import ua.lviv.iot.dto.assembler.IpAddressDtoAssembler;
import ua.lviv.iot.service.IpAddressService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ipaddresses")
public class IpAddressController {
    @Autowired
    private IpAddressService ipAddressService;
    @Autowired
    private IpAddressDtoAssembler ipAddressDtoAssembler;

    @GetMapping(value = "/{ip}")
    public ResponseEntity<IpAddressDto> getIpAddress(@PathVariable String ip) {
        IpAddress ipAddress = ipAddressService.findById(ip);
        IpAddressDto  ipAddressDto = ipAddressDtoAssembler.toModel(ipAddress);
        return new ResponseEntity<>(ipAddressDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<IpAddressDto>> getAllIpAddresss() {
        List<IpAddress> ipAddresss = ipAddressService.findAll();
        CollectionModel<IpAddressDto> ipAddressDtos = ipAddressDtoAssembler.toCollectionModel(ipAddresss);
        return new ResponseEntity<>(ipAddressDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<IpAddressDto> addIpAddress(@RequestBody IpAddress ipAddress) {
        IpAddress newIpAddress = ipAddressService.create(ipAddress);
        IpAddressDto ipAddressDto = ipAddressDtoAssembler.toModel(newIpAddress);
        return new ResponseEntity<>(ipAddressDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ip}")
    public ResponseEntity<?> updateIpAddress(@RequestBody IpAddress uIpAddress, @PathVariable String ip) {
        ipAddressService.update(ip, uIpAddress);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ip}")
    public ResponseEntity<?> deleteIpAddress(@PathVariable String ip) {
        ipAddressService.delete(ip);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/insertTenRowsInIpAddress")
    public void insertTenRowsInIpAddress(){
        ipAddressService.insertTenRowsInIpAddress();
    }

}
