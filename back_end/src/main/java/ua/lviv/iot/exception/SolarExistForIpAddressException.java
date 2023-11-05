package ua.lviv.iot.exception;

public class SolarExistForIpAddressException extends RuntimeException {
    public SolarExistForIpAddressException(Integer id) {
        super("There are available solar batteries/panels for 'ipAddress' with id=" + id);
    }
}
