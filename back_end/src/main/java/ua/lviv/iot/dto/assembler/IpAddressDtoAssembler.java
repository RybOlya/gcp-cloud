package ua.lviv.iot.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.controller.IpAddressController;
import ua.lviv.iot.domain.IpAddress;
import ua.lviv.iot.dto.CityDto;
import ua.lviv.iot.dto.IpAddressDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class IpAddressDtoAssembler implements RepresentationModelAssembler<IpAddress, IpAddressDto> {

    @Override
    public IpAddressDto toModel(IpAddress entity) {
        IpAddressDto ipAddressDto =IpAddressDto.builder()
                .ipAddress(entity.getIpAddress())
                .build();
        Link selfLink = linkTo(methodOn(IpAddressController.class).getIpAddress(ipAddressDto.getIpAddress())).withSelfRel();
        ipAddressDto.add(selfLink);
        return ipAddressDto;
    }

    @Override
    public CollectionModel<IpAddressDto> toCollectionModel(Iterable<? extends IpAddress> entities) {
        CollectionModel<IpAddressDto> ipAddressDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(IpAddressController.class).getAllIpAddresss()).withSelfRel();
        ipAddressDtos.add(selfLink);
        return ipAddressDtos;
    }
}
