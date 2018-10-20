package com.via.utils;

public class Constant {

  public enum Product {
    Flight, Hotel, Holiday;
  }

  public enum Country {
    SINGAPORE, INDONESIA, UAE, OMAN, THAILAND, PHILIPPINES, SAUDI, HONGKONG;
  }

  public enum CountryCode {
    SINGAPORE("SG"), INDONESIA("ID"), UAE("AE"), OMAN("OM"), THAILAND("TH"), PHILIPPINES("PH"), SAUDI(
        "SA"), HONGKONG("HK");

    String code;

    private CountryCode(String countryCode) {
      code = countryCode;
    }

    public String getCountryCode() {
      return code;
    }
  }
  public enum Booking_Media {
    B2B, B2C;
  }
  public enum JourneyType {
    ONE_WAY, ROUND_TRIP;
  }

  public enum Flight {
    DOMESTIC, INTERNATIONAL;
  }

  public enum PassengerType {
    ADULT, CHILD, INFANT;
  }

  public enum SelectBy {
    INDEX, VALUE, VISIBLETEXT;
  }


    public static final String CONFIGURATION_PROPERTIES            = "Config.properties";
    public static final String LOG_CONFIG_FILE                     = "log4j.xml";

    public static final String FIREFOX_GECKODRIVER_PATH            = System.getProperty("user.dir")
                                                                          + "/Driver/geckodriver";
    
    public static final String WINDOWS_CHROMEDRIVER_PATH           = System.getProperty("user.dir")
                                                                           + "/Driver/windowschromedriver.exe";
    public static final String MAC_CHROMEDRIVER_PATH               = System.getProperty("user.dir")
                                                                           + "/Driver/macchromedriver";
    
    public static final String WINDOWS_OPERADRIVER_PATH           = System.getProperty("user.dir")
                                                                           + "/Driver/operadriver.exe";
    public static final String MAC_OPERADRIVER_PATH               = System.getProperty("user.dir")
                                                                           + "/Driver/macoperadriver";

    public static final String IE_DRIVER_PATH                      = System.getProperty("user.dir")
                                                                           + "/Driver/IEDriverServer.exe";
    
    public static final String SCREENSHOTS_PATH                    = System.getProperty("user.dir") 
                                                                           + "/screenshots/";

    /* JSON FILES NAME */
    public static final String COMMON_LOCATOR_REPOSITORY           = "CommonLocatorRepository.JSON";
    public static final String FLIGHTS_LOCATOR_REPOSITORY          = "FlightsLocatorRepository.JSON";
    public static final String HOTELS_LOCATOR_REPOSITORY           = "HotelsLocatorRepository.JSON";
    public static final String BUSES_LOCATOR_REPOSITORY            = "BusesLocatorRepository.JSON";
    public static final String HOLIDAYS_LOCATOR_REPOSITORY         = "HolidaysLocatorRepository.JSON";
    public static final String RESELLER_COMMON_LOCATOR_REPOSITORY  = "ResellerCommonLocatorRepository.JSON";

    /* EXCEL SHEETS PATH */
    public static final String IN_FLIGHTS_TESTDATA                 = "/TestData/India/FlightExcelSheet.xlsx";
    public static final String TH_FLIGHTS_TESTDATA                 = "/TestData/Thailand/FlightExcelSheet.xlsx";
    public static final String ID_FLIGHTS_TESTDATA                 = "/TestData/Indonesia/FlightExcelSheet.xlsx";
    public static final String UAE_FLIGHTS_TESTDATA                = "/TestData/Uae/FlightExcelSheet.xlsx";
    public static final String OMAN_FLIGHTS_TESTDATA               = "/TestData/Oman/FlightExcelSheet.xlsx";
    public static final String SAUDI_FLIGHTS_TESTDATA              = "/TestData/Saudi/FlightExcelSheet.xlsx";
    public static final String HONGKONG_FLIGHTS_TESTDATA           = "/TestData/Hongkong/FlightExcelSheet.xlsx";
    public static final String SG_FLIGHTS_TESTDATA                 = "/TestData/Singapore/FlightExcelSheet.xlsx";
    public static final String PH_FLIGHTS_TESTDATA                 = "/TestData/Philippines/FlightExcelSheet.xlsx";

    /* SHEET NAME */
    public static final String FLIGHTS_DOMESTIC_SHEETNAME          = "Domestic";
    public static final String FLIGHTS_INTL_SHEETNAME              = "International";
    public static final String FLIGHTS_DOMESTIC_RESELLER_SHEETNAME = "DomesticReseller";
    public static final String FLIGHTS_INTL_RESELLER_SHEETNAME     = "InternationalReseller";

    public static final int    IMPLICIT_WAIT_TIME                  = 30;
    public static final int    PAGE_LOAD_TIMEOUT                   = 60;
    public static final String LOG_FILE_DATE_FORMAT                = "dd/MM/yyyy";

    /* SEPERATORS */
    public static final String WHITESPACE                          = " ";
    public static final String COMMA                               = ",";
    public static final String SLASH                               = "/";
    public static final String COLON                               = ":";
    public static final String PIPE                                = "|";
    public static final String UNDERSCORE                          = "_";
    public static final String HYPHEN                              = "-";
    public static final String FULLSTOP                            = ".";
    public static final String NEW_LINE                            = "\\r?\\n";
    public static final String QUESTION_MARK                       = "?";
    public static final String OPENING_PARANTHESIS                 = "(";
    public static final String METACHARACTERS                      = "<([{\\^-=$!|]})?*+.>";

    /* CONTACT DETAILS */
    public static final String ISD_CODE                            = "91";
    public static final String CONTACT_MOBILE                      = "9611577993";
    public static final String CONTACT_MOBILE_TH                   = "961157799";
    public static final String CONTACT_MOBILE_HK                   = "21660725";
    public static final String CONTACT_MOBILE_COMPLETE             = "+91-9611577993";
    public static final String CONTACT_LANDLINE                    = "8040433000,9723560302";
    public static final String CONTACT_EMAIL                       = "qa@via.com";
    public static final String CONTACT_CITY                        = "Bangalore";
    public static final String CONTACT_PINCODE                     = "560045";
    public static final String CONTACT_STREET                      = "Manyata";

    public static final String TITLE_ADULT                         = "Mr";
    public static final String TITLE_CHILD                         = "Mstr";
    public static final String TITLE_INFANT                        = "Mstr";
    public static final String BIRTH_DATE                          = "01";
    public static final String BIRTH_MONTH                         = "01";
    public static final String BIRTH_YEAR_ADULT                    = "1990";
    public static final String BIRTH_YEAR_CHILD                    = "2008";
    public static final String BIRTH_YEAR_INFANT                   = "2016";
    public static final String PASSPORT_DATE                       = "01";
    public static final String PASSPORT_MONTH                      = "01";
    public static final String PASSPORT_YEAR                       = "2030";
    public static final String AGE                                 = "30";
    public static final String ID_NUMBER                           = "TESTID";

    /* CARD DETAILS */
    //public static final String CARD_NUMBER                       = "5326760108614731";
    public static final String CARD_NUMBER                         = "5105105105105100";
    public static final String CARD_NAME                           = "Shiv Pratap";
    public static final String CARD_MONTH                          = "12";
    public static final String CARD_YEAR                           = "2020";
    public static final String CARD_CVV                            = "434";

    /* LOGIN DETAILS */
    public static final String ID_USERNAME                         = "ishan.bakshi@via.com";
    public static final String ID_PASSWORD                         = "ishan123";
    public static final String SG_USERNAME                         = "ankitkumar.t@via.com";
    public static final String SG_PASSWORD                         = "ankittripathi";
    public static final String UAE_USERNAME                        = "swetaankita.kumari@via.com";
    public static final String UAE_PASSWORD                        = "sweta121";
//    public static final String OMAN_USERNAME                       = "sweta.kumari@via.com";
//    public static final String OMAN_PASSWORD                       = "sweta121";
    public static final String OMAN_USERNAME                       = "anurag.srivastava@via.com";
    public static final String OMAN_PASSWORD                       = "password";
    public static final String SAUDI_USERNAME                      = "sweta.kumari@via.com";
    public static final String SAUDI_PASSWORD                      = "password";
    public static final String HONGKONG_USERNAME                   = "qatest@via.com";
    public static final String HONGKONG_PASSWORD                   = "welcome2via";
    public static final String TH_USERNAME                         = "ankitkumar.t@via.com";
    public static final String TH_PASSWORD                         = "ankittripathi";
    public static final String PH_USERNAME                         = "vikashkumar.singh@via.com";
    public static final String PH_PASSWORD                         = "viavikash";

    /* Agent Login Details */

    public static final String ID_ADMIN_USERNAME                   = "anjali.sahu@via.com";
    public static final String ID_ADMIN_PASSWORD                   = "anjali123";
    public static final String SG_ADMIN_USERNAME                   = "biswanath.d@via.com";
    public static final String SG_ADMIN_PASSWORD                   = "welcome2via";
    public static final String UAE_ADMIN_USERNAME                  = "biswanath.d@via.com";
    public static final String UAE_ADMIN_PASSWORD                  = "sweta@91";
    public static final String OMAN_ADMIN_USERNAME                 = "sweta.kumari@via.com";
    public static final String OMAN_ADMIN_PASSWORD                 = "sweta121";
    public static final String PH_ADMIN_USERNAME                   = "biswanath.d@via.com";
    public static final String PH_ADMIN_PASSWORD                   = "phworld";
    public static final String TH_ADMIN_USERNAME                   = "biswanath.d@via.com";
    public static final String TH_ADMIN_PASSWORD                   = "sweta@91";

    public static final String ID_DESK_USERNAME                    = "";
    public static final String ID_DESK_ID                          = "";
    public static final String ID_DESK_PASSWORD                    = "";
    public static final String SG_DESK_USERNAME                    = "";
    public static final String SG_DESK_ID                          = "";
    public static final String SG_DESK_PASSWORD                    = "";
    public static final String UAE_DESK_USERNAME                   = "";
    public static final String UAE_DESK_ID                         = "";
    public static final String UAE_DESK_PASSWORD                   = "";
    public static final String OMAN_DESK_USERNAME                  = "";
    public static final String OMAN_DESK_ID                        = "";
    public static final String OMAN_DESK_PASSWORD                  = "";
    public static final String PH_DESK_USERNAME                    = "";
    public static final String PH_DESK_ID                          = "";
    public static final String PH_DESK_PASSWORD                    = "";
    public static final String TH_DESK_USERNAME                    = "";
    public static final String TH_DESK_ID                          = "";
    public static final String TH_DESK_PASSWORD                    = "";

    public interface FlightsCarrier {
        public static final String AIRASIA_INDIA = "AirAsia India";
        public static final String AIR_COSTA     = "AirCosta";
        public static final String AIR_PEGASUS   = "AirPegasus";
        public static final String AIR_INDIA     = "AirIndia";
        public static final String GOAIR         = "GoAir";
        public static final String INDIGO        = "IndiGo";
        public static final String JET_AIRWAYS   = "JetAirways";
        public static final String SPICEJET      = "SpiceJet";
        public static final String TRUJET        = "TruJet";
        public static final String VISTARA       = "Vistara";
        public static final String TIGER_AIRWAYS = "Tiger Airways";
    }
}
