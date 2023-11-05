package ua.lviv.iot.exception;

public class SolarPanelNotFoundException extends RuntimeException {
    public SolarPanelNotFoundException(Integer id) {
        super("Could not find 'solarPanel' with id=" + id);
    }
}
