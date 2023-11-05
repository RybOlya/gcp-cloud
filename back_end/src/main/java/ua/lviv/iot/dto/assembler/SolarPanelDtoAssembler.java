package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.ClientController;
import ua.lviv.iot.controller.SolarPanelController;
import ua.lviv.iot.controller.SolarPanelController;
import ua.lviv.iot.domain.SolarPanel;
import ua.lviv.iot.dto.SolarPanelDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class SolarPanelDtoAssembler implements RepresentationModelAssembler<SolarPanel, SolarPanelDto> {
@Override
public SolarPanelDto toModel(SolarPanel entity) {
    SolarPanelDto solarPanelDto = SolarPanelDto.builder()
            .id(entity.getId())
            .ipAddress(entity.getIpAddress().getIpAddress())
            .model(entity.getModel())
            .type(entity.getType())
            .solarSystemId(entity.getSolarSystem().getId())
            .currentAngle(entity.getCurrentAngle())
            .build();
    Link selfLink =
            linkTo(methodOn(SolarPanelController.class).getSolarPanel(solarPanelDto.getId())).withSelfRel();
    solarPanelDto.add(selfLink);
    return solarPanelDto;
}

    @Override
    public CollectionModel<SolarPanelDto> toCollectionModel(Iterable<? extends SolarPanel> entities) {
        CollectionModel<SolarPanelDto> solarPanelDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(SolarPanelController.class).getAllSolarPanels()).withSelfRel();
        solarPanelDtos.add(selfLink);
        return solarPanelDtos;
    }

    public CollectionModel<SolarPanelDto> toCollectionModel(Iterable<? extends SolarPanel> entities, Link link) {
        CollectionModel<SolarPanelDto> solarPanelDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        solarPanelDtos.add(link);
        return solarPanelDtos;
    }
}
