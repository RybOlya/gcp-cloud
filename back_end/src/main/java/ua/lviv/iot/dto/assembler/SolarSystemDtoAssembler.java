package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.SolarSystemController;
import ua.lviv.iot.domain.SolarSystem;
import ua.lviv.iot.dto.SolarSystemDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class SolarSystemDtoAssembler implements RepresentationModelAssembler<SolarSystem, SolarSystemDto> {
    @Override
    public SolarSystemDto toModel(SolarSystem entity) {
        SolarSystemDto solarSystemDto =SolarSystemDto.builder()
                .id(entity.getId())
                .feedInTariff(entity.getFeedInTariff())
                .energySold(entity.getEnergySold())
                .street(entity.getStreet())
                .apartment(entity.getApartment())
                .city(entity.getCity().getCity())
                .build();
        Link selfLink =
                linkTo(methodOn(SolarSystemController.class).getSolarSystem(solarSystemDto.getId())).withSelfRel();
        solarSystemDto.add(selfLink);
        return solarSystemDto;
    }

    @Override
    public CollectionModel<SolarSystemDto> toCollectionModel(Iterable<? extends SolarSystem> entities) {
        CollectionModel<SolarSystemDto> solarSystemDtos =
                RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(SolarSystemController.class).getAllSolarSystems()).withSelfRel();
        solarSystemDtos.add(selfLink);
        return solarSystemDtos;
    }

    public CollectionModel<SolarSystemDto> toCollectionModel(Iterable<? extends SolarSystem> entities, Link link) {
        CollectionModel<SolarSystemDto> solarSystemDtos =
                RepresentationModelAssembler.super.toCollectionModel(entities);
        solarSystemDtos.add(link);
        return solarSystemDtos;
    }
}
