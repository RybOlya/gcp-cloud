package ua.lviv.iot.dto.assembler.many_to_many;



import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.many_to_many.SolarSystemClientController;
import ua.lviv.iot.domain.many_to_many.SolarSystemClient;
import ua.lviv.iot.dto.many_to_many.SolarSystemClientDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class SolarSystemClientDtoAssembler implements RepresentationModelAssembler<SolarSystemClient,
        SolarSystemClientDto> {
    @Override
    public SolarSystemClientDto toModel(SolarSystemClient entity) {
        SolarSystemClientDto solarSystemClientDto = SolarSystemClientDto.builder()
                .id(entity.getId())
                .solarSystemId(entity.getSolarSystem().getId())
                .clientSurname(entity.getClient().getSurname())
                .build();

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SolarSystemClientController.class)
                .getSolarSystemClientById(entity.getId())).withSelfRel();

        solarSystemClientDto.add(selfLink);

        return solarSystemClientDto;
    }

    @Override
    public CollectionModel<SolarSystemClientDto> toCollectionModel(Iterable<? extends SolarSystemClient> entities) {
        CollectionModel<SolarSystemClientDto> solarSystemClientDtos =
                RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(SolarSystemClientController.class).getAllSolarSystemClient()).withSelfRel();
        solarSystemClientDtos.add(selfLink);
        return solarSystemClientDtos;
    }
}