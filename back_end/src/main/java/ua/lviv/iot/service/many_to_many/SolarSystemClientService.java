package ua.lviv.iot.service.many_to_many;

import org.springframework.data.jpa.repository.query.Procedure;
import ua.lviv.iot.domain.many_to_many.SolarSystemClient;
import ua.lviv.iot.service.GeneralService;

import javax.transaction.Transactional;
import java.util.List;

public interface SolarSystemClientService extends GeneralService<SolarSystemClient, Integer> {
    List<SolarSystemClient> getSolarSystemClientBySolarSystemId(int solarSystemId);
    List<SolarSystemClient> getSolarSystemClientByClientId(int clientId);
    String insertIntoSolarSystemClient( Integer solarSystemId, String clientSurname);
}