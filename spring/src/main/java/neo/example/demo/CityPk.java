package neo.example.demo;

import java.io.Serializable;

public class CityPk implements Serializable {

    private String cityName;
    private String countryName;

    public CityPk() {
    }

    public CityPk(String cityName, String countryName) {
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
