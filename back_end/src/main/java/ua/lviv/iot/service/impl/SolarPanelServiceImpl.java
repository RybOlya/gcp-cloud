package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.*;
import ua.lviv.iot.exception.*;
import ua.lviv.iot.repository.CityRepository;
import ua.lviv.iot.repository.IpAddressRepository;
import ua.lviv.iot.repository.SolarPanelRepository;
import ua.lviv.iot.repository.SolarSystemRepository;
import ua.lviv.iot.service.SolarPanelService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class SolarPanelServiceImpl implements SolarPanelService {
    @Autowired
    IpAddressRepository ipAddressRepository;
    @Autowired
    SolarPanelRepository solarPanelRepository;
    @Autowired
    SolarSystemRepository solarSystemRepository;

    public SolarPanel findById(Integer id) {
        return solarPanelRepository.findById(id)
                .orElseThrow(() -> new SolarPanelNotFoundException(id));
    }
    public List<SolarPanel> findAll() {
        return solarPanelRepository.findAll();
    }

    @Transactional
    public SolarPanel create(SolarPanel solarPanel) {
        solarPanelRepository.save(solarPanel);
        return solarPanel;
    }

    @Transactional
    public SolarPanel create(SolarPanel solarPanel,  String ip,  Integer solarSystemId) {
        IpAddress ipAddress = ipAddressRepository.findById(ip)
                .orElseThrow(() -> new IpAddressNotFoundException(ip));
        solarPanel.setIpAddress(ipAddress);
        SolarSystem solarSystem = solarSystemRepository.findById(solarSystemId)
                .orElseThrow(() -> new SolarSystemNotFoundException(solarSystemId));
        solarPanel.setSolarSystem(solarSystem);
        solarPanelRepository.save(solarPanel);
        return solarPanel;
    }


    @Transactional
    public void update(Integer solarPanelId, SolarPanel uSolarPanel) {

        SolarPanel solarPanel = solarPanelRepository.findById(solarPanelId)
                .orElseThrow(() -> new SolarPanelNotFoundException(solarPanelId));
        //update
        solarPanel.setModel(uSolarPanel.getModel());
        solarPanel.setIpAddress(uSolarPanel.getIpAddress());
        solarPanel.setSolarSystem(uSolarPanel.getSolarSystem());
        solarPanel.setCurrentAngle(uSolarPanel.getCurrentAngle());
        solarPanelRepository.save(solarPanel);

    }


    @Transactional
    public void update(Integer solarPanelId, SolarPanel uSolarPanel, String ip,  Integer solarSystemId) {
        IpAddress ipAddress = ipAddressRepository.findById(ip)
                .orElseThrow(() -> new IpAddressNotFoundException(ip));
        SolarSystem solarSystem = solarSystemRepository.findById(solarSystemId)
                .orElseThrow(() -> new SolarSystemNotFoundException(solarSystemId));
        SolarPanel solarPanel = solarPanelRepository.findById(solarPanelId)
                .orElseThrow(() -> new SolarPanelNotFoundException(solarPanelId));
        //update
        solarPanel.setModel(uSolarPanel.getModel());
        solarPanel.setIpAddress(ipAddress);
        solarPanel.setSolarSystem(solarSystem);
        solarPanel.setCurrentAngle(uSolarPanel.getCurrentAngle());
        solarPanelRepository.save(solarPanel);
    }

    @Transactional
    public void delete(Integer id) {
        SolarPanel solarPanel = solarPanelRepository.findById(id)
                .orElseThrow(() -> new SolarPanelNotFoundException(id));
        solarPanelRepository.delete(solarPanel);
    }

    @Override
    public List<SolarPanel> findSolarPanelsByIpAddress(String ip) {
        return solarPanelRepository.findByIpAddress(ip);
    }
}
