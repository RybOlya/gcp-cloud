package ua.lviv.iot.service;

import ua.lviv.iot.domain.Client;
import ua.lviv.iot.domain.SolarSystem;

import java.util.List;

public interface ClientService extends GeneralService<Client, Integer>{
    Client create(Client entity, Integer cityId);

    void update(Integer clientId, Client uClient, Integer cityId);

    public List<Client> findClientsByCityId(Integer cityId);

    public List<Client> getClientsBySolarSystemId(Integer solarSystemId);

    public Client addSolarSystemForClient(Integer clientId, Integer solarSystemId);

    public Client removeSolarSystemForClient(Integer clientId, Integer solarSystemId);

    public List<SolarSystem> findSolarSystemsById(Integer id);
}
