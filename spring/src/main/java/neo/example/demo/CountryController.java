package neo.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping(value = "country-by-continent-or-country-name")
    public List<Country> getCountriesByContinentOrCountryName(@RequestParam(required = false) String continentName, @RequestParam(required = false) String countryName) {
        if (continentName == null && countryName == null) {
            return countryRepository.findAll();
        } else if (countryName == null) {
            return countryRepository.findCountriesByContinent(new Continent(continentName));
        } else if (continentName == null) {
            return countryRepository.findCountriesByCountryName(countryName);
        } else {
            return countryRepository.findCountriesByCountryNameAndContinent(countryName, new Continent(continentName));
        }
    }

    @GetMapping(value = "country-by-city-name")
    public List<Country> getCountriesByCityName(@RequestParam String cityName) {
        return countryRepository.getCountriesByCity(cityName);
    }

    @PostMapping(value = "country")
    public ResponseEntity<Country> addCountry(@RequestParam String countryName, @RequestParam String continentName) {
        Country saved = countryRepository.save(new Country(countryName, new Continent(continentName)));
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping(value = "country")
    public ResponseEntity<String> removeCountries(@RequestParam(required = false) String countryName, @RequestParam(required = false) String continentName) {
        if (continentName == null && countryName == null) {
            countryRepository.deleteAll();
        } else if (countryName == null) {
            countryRepository.deleteAllByContinent(new Continent(continentName));
        } else if (continentName == null) {
            countryRepository.deleteAllByCountryName(countryName);
        } else {
            countryRepository.deleteAllByCountryNameAndContinent(countryName, new Continent(continentName));
        }
        return ResponseEntity.ok().build();
    }

}
