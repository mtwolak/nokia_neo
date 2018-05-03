package neo.example.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CitySerializer extends StdSerializer<City> {

    public CitySerializer() {
        this(null);
    }

    public CitySerializer(Class<City> t) {
        super(t);
    }

    @Override
    public void serialize(City value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("cityName", value.getCityName());
        gen.writeStringField("countryName", value.getCountry().getCountryName());
        gen.writeStringField("continentName", value.getCountry().getContinent().getContinentName());
        gen.writeEndObject();
    }
}
