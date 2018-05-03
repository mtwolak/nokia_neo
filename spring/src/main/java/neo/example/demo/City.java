package neo.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(CityPk.class)
@JsonSerialize(using = CitySerializer.class)
public class City implements Serializable {

    @Id
    @Column(name = "CITY_NAME")
    private String cityName;

    @Id
    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "COUNTRY_NAME", insertable = false, updatable = false)
    private Country country;

    public City() {
    }

    public City(String cityName, Country country) {
        this.cityName = cityName;
        this.countryName = country.getCountryName();
        this.country = country;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.countryName = country.getCountryName();
        this.country = country;
    }

}
