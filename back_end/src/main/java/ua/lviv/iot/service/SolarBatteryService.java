package ua.lviv.iot.service;

import ua.lviv.iot.domain.Client;
import ua.lviv.iot.domain.SolarBattery;
import ua.lviv.iot.domain.SolarBattery;

import java.util.List;

public interface SolarBatteryService extends GeneralService<SolarBattery, Integer>{
    List<SolarBattery> findSolarBatteriesByIpAddress(String ip);
    SolarBattery create(SolarBattery entity, String ip, Integer solarSystemId);

    void update(Integer solarBatteryId, SolarBattery uSolarBattery,String ip,Integer solarSystemId);
}
