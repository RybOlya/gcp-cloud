package ua.lviv.iot.exception;

import com.sun.xml.bind.v2.model.core.ID;

public class IpAddressNotFoundException extends RuntimeException {
    public IpAddressNotFoundException(String ip) {
        super("Couldn't find ip address " + ip);
    }
}
