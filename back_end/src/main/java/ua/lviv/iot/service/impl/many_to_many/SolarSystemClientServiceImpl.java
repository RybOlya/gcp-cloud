package ua.lviv.iot.service.impl.many_to_many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.many_to_many.SolarSystemClient;
import ua.lviv.iot.exception.SolarSystemNotFoundException;
import ua.lviv.iot.repository.ClientRepository;
import ua.lviv.iot.repository.SolarSystemRepository;
import ua.lviv.iot.repository.many_to_many.SolarSystemClientRepository;
import ua.lviv.iot.service.many_to_many.SolarSystemClientService;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class SolarSystemClientServiceImpl implements SolarSystemClientService {
    @Autowired
    SolarSystemClientRepository solarSystemClientRepository;

    @Autowired
    SolarSystemRepository solarSystemRepository;

    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<SolarSystemClient> findAll() {
        return solarSystemClientRepository.findAll();
    }

    @Override
    public SolarSystemClient findById(Integer id) {
        return solarSystemClientRepository.findById(id)
                .orElseThrow(() -> new SolarSystemNotFoundException(id));

    }

    @Override
    @Transactional
    public SolarSystemClient create(SolarSystemClient item) {
        return solarSystemClientRepository.insertInSolarSystemClient(
                item.getSolarSystem().getId(), item.getClient().getId()
        );
    }

    @Override
    @Transactional
    public void update(Integer id, SolarSystemClient item) {
        solarSystemClientRepository.updateSolarSystemClient(
                id, item.getSolarSystem().getId(), item.getClient().getId()
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        solarSystemClientRepository.findById(id)
                .orElseThrow(() -> new SolarSystemNotFoundException(id));

        solarSystemClientRepository.deleteById(id);
    }
    @Override
    public String insertIntoSolarSystemClient(Integer solarSystemId,String clientSurname){
        return ("Newly inserted row id: " + solarSystemClientRepository.insertIntoSolarSystemClient(solarSystemId,clientSurname));
    }

    @Override
    public List<SolarSystemClient> getSolarSystemClientBySolarSystemId(int solarSystemId) {
        return null;
    }

    @Override
    public List<SolarSystemClient> getSolarSystemClientByClientId(int clientId) {
        return null;
    }
}