package com.via.pageobjects.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDetails {

    private String  name;
    private String  code;
    private boolean refundable;
    private String  operatingCarrier;

    private String  sourceCity;
    private String  sourceCityCode;
    private String  departureTime;
    private String  departureDate;

    private String  destinationCity;
    private String  destinationCityCode;
    private String  arrivalTime;
    private String  arrivalDate;

    private String  duration;

}
