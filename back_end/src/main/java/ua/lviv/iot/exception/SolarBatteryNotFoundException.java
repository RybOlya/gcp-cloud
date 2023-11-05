package ua.lviv.iot.exception;

public class SolarBatteryNotFoundException extends RuntimeException {
    public SolarBatteryNotFoundException(Integer id) {
        super("Could not find 'solarBattery' with id=" + id);
    }
}
