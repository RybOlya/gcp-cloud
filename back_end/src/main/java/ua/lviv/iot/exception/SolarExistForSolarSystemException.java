package ua.lviv.iot.exception;

public class SolarExistForSolarSystemException extends RuntimeException {
    public SolarExistForSolarSystemException(Integer id) {
        super("There are available solar batteries/panels for 'solarSystem' with id=" + id);
    }
}
