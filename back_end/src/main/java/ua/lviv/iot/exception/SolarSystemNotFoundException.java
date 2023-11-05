package ua.lviv.iot.exception;

public class SolarSystemNotFoundException extends RuntimeException {
    public SolarSystemNotFoundException(Integer id) {
        super("Could not find 'solarSystem' with id=" + id);
    }
}
