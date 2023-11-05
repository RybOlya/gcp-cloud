package ua.lviv.iot.dto.many_to_many;

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
@Relation(itemRelation = "", collectionRelation = "")
public class SolarSystemClientDto extends RepresentationModel<SolarSystemClientDto> {
    private int id;
    private int solarSystemId;
    private String clientSurname;
}