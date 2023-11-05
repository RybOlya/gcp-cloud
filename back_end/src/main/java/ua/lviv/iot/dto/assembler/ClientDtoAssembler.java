package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.ClientController;
import ua.lviv.iot.domain.Client;
import ua.lviv.iot.dto.ClientDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<Client, ClientDto> {
    @Override
    public ClientDto toModel(Client entity) {
        ClientDto clientDto =ClientDto.builder()
                .id(entity.getId())
                .surname(entity.getSurname())
                .name(entity.getName())
                .email(entity.getEmail())
                .street(entity.getStreet())
                .apartment(entity.getApartment())
                .city(entity.getCity().getCity())
                .build();
        Link selfLink = linkTo(methodOn(ClientController.class).getClient(clientDto.getId())).withSelfRel();
        clientDto.add(selfLink);
        Link solarSystemsLink =
                linkTo(methodOn(ClientController.class).getAllSolarSystemsForClient(entity.getId())).withRel(
                "solarSystems");
        clientDto.add(solarSystemsLink);
        return clientDto;
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel();
        clientDtos.add(selfLink);
        return clientDtos;
    }

    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities, Link link) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        clientDtos.add(link);
        return clientDtos;
    }
}
