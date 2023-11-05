package ua.lviv.iot.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Integer id) {
        super("Couldn't find country with id: " + id);
    }
}