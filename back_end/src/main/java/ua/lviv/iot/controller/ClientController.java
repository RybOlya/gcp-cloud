package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.domain.Client;
import ua.lviv.iot.domain.SolarSystem;
import ua.lviv.iot.dto.ClientDto;
import ua.lviv.iot.dto.SolarSystemDto;
import ua.lviv.iot.dto.assembler.ClientDtoAssembler;
import ua.lviv.iot.dto.assembler.SolarSystemDtoAssembler;
import ua.lviv.iot.service.ClientService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


    @RestController
    @RequestMapping(value = "/api/clients")
    public class ClientController {
        @Autowired
        private ClientService clientService;
        @Autowired
        private ClientDtoAssembler clientDtoAssembler;
        @Autowired
        private SolarSystemDtoAssembler solarSystemDtoAssembler;

        @GetMapping(value = "/{clientId}")
        public ResponseEntity<ClientDto> getClient(@PathVariable Integer clientId) {
            Client client = clientService.findById(clientId);
            ClientDto clientDto = clientDtoAssembler.toModel(client);
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        }


        @PostMapping(value = "/{cityId}")
        public ResponseEntity<ClientDto> addClientWithCity(@RequestBody Client client, @PathVariable Integer cityId) {
            Client newClient = clientService.create(client, cityId);
            ClientDto clientDto = clientDtoAssembler.toModel(newClient);
            return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
        }

        @PutMapping(value = "/{clientId}")
        public ResponseEntity<?> updateClient(@RequestBody Client uClient, @PathVariable Integer clientId) {
            clientService.update(clientId, uClient);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PutMapping(value = "/{clientId}/cities/{cityId}")
        public ResponseEntity<?> updateClientWithCity(@RequestBody Client uClient, @PathVariable Integer clientId,
                                                      @PathVariable Integer cityId) {
            clientService.update(clientId, uClient, cityId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @DeleteMapping(value = "/{clientId}")
        public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) {
            clientService.delete(clientId);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping(value = "")
        public ResponseEntity<CollectionModel<ClientDto>> getAllClients() {
            List<Client> clients = clientService.findAll();
            CollectionModel<ClientDto> clientDtos = clientDtoAssembler.toCollectionModel(clients);
            return new ResponseEntity<>(clientDtos, HttpStatus.OK);
        }

        // ------------------------------------------------------------------------------
        @GetMapping(value = "cities/{cityId}")
        public ResponseEntity<CollectionModel<ClientDto>> getClientsByCityId(@PathVariable Integer cityId) {
            List<Client> clients = clientService.findClientsByCityId(cityId);
            Link selfLink = linkTo(methodOn(ClientController.class).getClientsByCityId(cityId)).withSelfRel();
            CollectionModel<ClientDto> clientDtos = clientDtoAssembler.toCollectionModel(clients, selfLink);
            return new ResponseEntity<>(clientDtos, HttpStatus.OK);
        }

        @GetMapping(value = "solarSystem/{solarSystemId}")
        public ResponseEntity<CollectionModel<ClientDto>> getClientsBySolarSystemId(@PathVariable Integer solarSystemId) {
            List<Client> clients = clientService.findClientsByCityId(solarSystemId);
            Link selfLink =
                    linkTo(methodOn(ClientController.class).getClientsBySolarSystemId(solarSystemId)).withSelfRel();
            CollectionModel<ClientDto> clientDtos = clientDtoAssembler.toCollectionModel(clients, selfLink);
            return new ResponseEntity<>(clientDtos, HttpStatus.OK);
        }
        @GetMapping(value = "/{clientId}/solarSystems")
        public ResponseEntity<CollectionModel<SolarSystemDto>> getAllSolarSystemsForClient(@PathVariable Integer clientId) {
            List<SolarSystem> solarSystems = clientService.findSolarSystemsById(clientId);
            Link selfLink = linkTo(methodOn(ClientController.class).getAllSolarSystemsForClient(clientId)).withSelfRel();
            CollectionModel<SolarSystemDto> bookDtos = solarSystemDtoAssembler.toCollectionModel(solarSystems,
                    selfLink);
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        }
        // ------------------------------------------------------------------------------
        @PostMapping(value = "client/{clientId}/solarSystem/{solarSystemId}")
        public ResponseEntity<ClientDto> addSolarSystemForClient(@PathVariable Integer clientId,
                                                          @PathVariable Integer solarSystemId) {
            Client client = clientService.addSolarSystemForClient(clientId, solarSystemId);
            ClientDto clientDto = clientDtoAssembler.toModel(client);
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        }

        @DeleteMapping(value = "client/{clientId}/solarSystem/{solarSystemId}")
        public ResponseEntity<ClientDto> removeSolarSystemForClient(@PathVariable Integer clientId,
                                                           @PathVariable Integer solarSystemId) {
            Client client = clientService.removeSolarSystemForClient(clientId, solarSystemId);
            ClientDto clientDto = clientDtoAssembler.toModel(client);
            return new ResponseEntity<>(clientDto, HttpStatus.OK);
        }

    }
