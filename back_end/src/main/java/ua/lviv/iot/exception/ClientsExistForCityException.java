package ua.lviv.iot.exception;

public class ClientsExistForCityException extends RuntimeException {
    public ClientsExistForCityException(Integer id) {
        super("There are available clients for 'city' with id=" + id);
    }
}
