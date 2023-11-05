package ua.lviv.iot.exception;

public class ClientNotFoundException  extends RuntimeException {
        public ClientNotFoundException(Integer id) {
            super("Could not find 'client' with id=" + id);
        }
    }

