package ua.lviv.iot.exception;

public class ClientHasSolarSystemException extends RuntimeException {
    public ClientHasSolarSystemException(Integer solarSystemId, Integer clientId) {
        super("There are available 'solarSystem' with id=" + solarSystemId+" in 'client' with id=" +clientId);
    }
}
