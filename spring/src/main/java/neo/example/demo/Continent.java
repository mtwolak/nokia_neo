package neo.example.demo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "Continent")
public class Continent implements Serializable {

    @Id
    @Column(name = "CONTINENT_NAME")
    private String continentName;

    @OneToMany(mappedBy = "continent", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Country> countries;

    public Continent() {
    }

    public Continent(String continentName) {
        this.continentName = continentName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

}
