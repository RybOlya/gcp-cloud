package ua.lviv.iot.service;


import ua.lviv.iot.domain.Client;
import ua.lviv.iot.domain.SolarPanel;

import java.util.List;


public interface SolarPanelService extends GeneralService<SolarPanel, Integer>{

    SolarPanel create(SolarPanel entity, String ip,Integer solarSystemId);

    void update(Integer solarPanelId, SolarPanel uSolarPanel,String ip,Integer solarSystemId);
    List<SolarPanel> findSolarPanelsByIpAddress(String ip);
}
