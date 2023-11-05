package ua.lviv.iot.service;

import ua.lviv.iot.domain.IpAddress;

public interface IpAddressService extends GeneralService<IpAddress, String>{
    void insertTenRowsInIpAddress();
}