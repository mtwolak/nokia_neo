package neo.example.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CountrySerializer extends StdSerializer<Country> {

    public CountrySerializer() {
        this(null);
    }

    public CountrySerializer(Class<Country> t) {
        super(t);
    }

    @Override
    public void serialize(Country value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("countryName", value.getCountryName());
        gen.writeStringField("continentName", value.getContinent().getContinentName());
        gen.writeEndObject();
    }

}
