package ua.lviv.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "solarPanel", collectionRelation = "solarPanels")
public class SolarPanelDto extends RepresentationModel<SolarPanelDto> {
    private final Integer id;
    private final String ipAddress;
    private final String model;
    private final String type;
    private final Float currentAngle;
    private final Integer solarSystemId;
}
