package neo.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonSerialize(using = CountrySerializer.class)
public class Country implements Serializable {

    @Id
    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CONTINENT_NAME")
    private Continent continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<City> city = new HashSet<>();

    public Country() {
    }

    public Country(String countryName, Continent continent) {
        this.countryName = countryName;
        this.continent = continent;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
