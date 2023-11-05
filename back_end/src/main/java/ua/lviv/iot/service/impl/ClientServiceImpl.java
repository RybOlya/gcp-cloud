package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.City;
import ua.lviv.iot.domain.Client;
import ua.lviv.iot.domain.SolarSystem;
import ua.lviv.iot.exception.*;
import ua.lviv.iot.repository.CityRepository;
import ua.lviv.iot.repository.ClientRepository;
import ua.lviv.iot.repository.SolarSystemRepository;
import ua.lviv.iot.service.ClientService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    SolarSystemRepository solarSystemRepository;

    public Client findById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client create(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public Client create(Client client, Integer cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
        client.setCity(city);
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public void update(Integer clientId, Client uClient) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        //update
        client.setSurname(uClient.getSurname());
        client.setName(uClient.getName());
        client.setEmail(uClient.getEmail());
        client.setStreet(uClient.getStreet());
        client.setApartment(uClient.getApartment());
        clientRepository.save(client);
    }

    @Transactional
    public void update(Integer clientId, Client uClient, Integer cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        //update
        client.setSurname(uClient.getSurname());
        client.setName(uClient.getName());
        client.setEmail(uClient.getEmail());
        client.setStreet(uClient.getStreet());
        client.setApartment(uClient.getApartment());
        client.setCity(city);
        clientRepository.save(client);
    }

    @Transactional
    public void delete(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        clientRepository.delete(client);
    }

    // ------------------------------------------------------------------------------

    public List<SolarSystem> findSolarSystemsById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        return client.getSolarSystems().stream().toList();
    }

    public List<Client> findClientsByCityId(Integer cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
        return city.getClients();
    }

    public List<Client> getClientsBySolarSystemId(Integer solarSystemId) {
        SolarSystem solarSystem = solarSystemRepository.findById(solarSystemId)
                .orElseThrow(() -> new SolarSystemNotFoundException(solarSystemId));
        return solarSystem.getClients().stream().toList();
    }

    @Transactional
    public Client addSolarSystemForClient(Integer clientId, Integer solarSystemId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        SolarSystem solarSystem = solarSystemRepository.findById(solarSystemId)
                .orElseThrow(() -> new SolarSystemNotFoundException(solarSystemId));
        if (client.getSolarSystems().contains(solarSystem)) throw new ClientHasSolarSystemException(solarSystemId,
                clientId);
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public Client removeSolarSystemForClient(Integer clientId, Integer solarSystemId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        SolarSystem solarSystem = solarSystemRepository.findById(solarSystemId)
                .orElseThrow(() -> new SolarSystemNotFoundException(solarSystemId));
        if (!client.getSolarSystems().contains(solarSystem)) throw new ClientHasNoSolarSystemException(solarSystemId,
                clientId);
        client.getSolarSystems().remove(solarSystem);
        clientRepository.save(client);
        return client;
    }
}
