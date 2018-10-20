package com.via.pageobjects.flights;

import java.util.Calendar;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.via.utils.Constant.Flight;
import com.via.utils.Constant.JourneyType;

@Getter
@Setter
public class FlightBookingDetails {

  private JourneyType journeyType = JourneyType.ONE_WAY;
  private Flight flight = Flight.DOMESTIC;

  private String sourceCity;
  private String sourceCityCode;
  private String destinationCity;
  private String destinationCityCode;

  private Calendar onwardDate;
  private Calendar returnDate;

  private int adultsCount = 1;
  private int childrenCount = 0;
  private int infantsCount = 0;

  private String onwardFlightName;
  private String onwardFlightCode;
  private int onwardFlightIndex = 1;
  private String returnFlightName;
  private String returnFlightCode;
  private int returnFlightIndex = 1;

  private boolean mealsFlag = false;
  private boolean baggageFlag = false;
  private boolean seatsFlag = false;

  private List<FlightDetails> flights;

}
