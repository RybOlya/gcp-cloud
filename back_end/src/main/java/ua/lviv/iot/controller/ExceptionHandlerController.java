package ua.lviv.iot.controller;

import ua.lviv.iot.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityNotFoundHandler(CityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClientHasNoSolarSystemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientHasNoSolarSystemException(ClientHasNoSolarSystemException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClientHasSolarSystemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientHasSolarSystemException(ClientHasSolarSystemException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientNotFoundHandler(ClientNotFoundException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(ClientsExistForCityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientsExistForCityException(ClientsExistForCityException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IpAddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ipAddressNotFoundException(IpAddressNotFoundException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(SolarBatteryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String solarBatteryNotFoundException(SolarBatteryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SolarExistForIpAddressException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String solarExistForIpAddressException(SolarExistForIpAddressException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SolarExistForSolarSystemException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String solarExistForSolarSystemException(SolarExistForSolarSystemException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SolarSystemNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String solarSystemNotFoundException(SolarSystemNotFoundException ex) {
        return ex.getMessage();
    }

}
