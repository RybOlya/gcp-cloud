package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.IpAddress;
import ua.lviv.iot.exception.IpAddressNotFoundException;
import ua.lviv.iot.repository.IpAddressRepository;
import ua.lviv.iot.service.IpAddressService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class IpAddressServiceImpl implements IpAddressService {
    @Autowired
    IpAddressRepository ipAddressRepository;

    @Override
    public List<IpAddress> findAll() {
        return ipAddressRepository.findAll();
    }

    @Override
    public IpAddress findById(String ipAddress) {
        return ipAddressRepository.findById(ipAddress).orElseThrow(() -> new IpAddressNotFoundException(ipAddress));
    }

    @Override
    @Transactional
    public IpAddress create(IpAddress ipAddress) {
        return new IpAddress() {
            {
                setIpAddress(ipAddressRepository.insertIntoIpAddress(ipAddress.getIpAddress()));
            }
        };
    }

    @Override
    @Transactional
    public void update(String ipAddress, IpAddress uIpAddress) {
        IpAddress ipAddressEntity = ipAddressRepository.findById(ipAddress)
                .orElseThrow(() -> new IpAddressNotFoundException(ipAddress));
        ipAddressEntity.setIpAddress(uIpAddress.getIpAddress());
    }

    @Override
    @Transactional
    public void delete(String ipAddress) {
        IpAddress ipAddressEntity = ipAddressRepository.findById(ipAddress)
                .orElseThrow(() -> new IpAddressNotFoundException(ipAddress));
        ipAddressRepository.delete(ipAddressEntity);
    }
    @Override
    public void insertTenRowsInIpAddress() {
        ipAddressRepository.insertTenRowsInIpAddress();
    }
}
