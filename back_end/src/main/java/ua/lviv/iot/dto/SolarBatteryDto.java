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
@Relation(itemRelation = "solarBattery", collectionRelation = "solarBatteries")
public class SolarBatteryDto  extends RepresentationModel<SolarBatteryDto> {
    private final Integer id;
    private final String ipAddress;
    private final String model;
    private final Double capacity;
    private final Integer operatingTemperature;
    private final Integer solarSystemId;
}
