package ua.lviv.iot.exception;

public class ClientHasNoSolarSystemException extends RuntimeException {
    public ClientHasNoSolarSystemException(Integer solarSystemId, Integer clientId) {
        super("There are no available 'solarSystem' with id=" + solarSystemId+" in 'client' with id=" +clientId);
    }
}
