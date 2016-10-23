package com.addhen.serializer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class SerializerTest extends BaseTestCase {

    private Serializer mSerializer;

    @Before
    public void setUp() {
        mSerializer = new Serializer.Builder().build();
    }

    @Test
    public void shouldSuccessfullySerializeToJsonStrings() {
        String jsonString = getJsonStrings();
        assertNotNull(jsonString);
        assertEquals(
                "[{\"name\":\"Silverbird\",\"locationName\":\"Accra Mall\",\"location\":"
                        + "{\"latitude\":0.0,\"longitude\":0.0}}]",
                jsonString);
    }

    @Test
    public void shouldSuccessfullySerializeToJsonString() {
        String jsonString = getJsonString();
        assertNotNull(jsonString);
        assertEquals(
                "{\"name\":\"Silverbird\",\"locationName\":\"Accra Mall\",\"location\":"
                        + "{\"latitude\":0.0,\"longitude\":0.0}}",
                jsonString);
    }

    @Test
    public void shouldSuccessfullyDeserializeStringToJson() {
        String jsonString = getJsonString();
        Cinema cinema = mSerializer.serializationStrategy(Cinema.class).deserialize(jsonString);
        assertNotNull(cinema);
        assertCinema(cinema);
    }

    @Test
    public void shouldSuccessfullyDeserializeStringToList() {
        String jsonString = getJsonStrings();
        List<Cinema> cinemas = Arrays
                .asList(mSerializer.serializationStrategy(Cinema[].class).deserialize(jsonString));
        assertNotNull(cinemas);
        assertEquals(1, cinemas.size());
        assertCinema(cinemas.get(0));
    }

    private void assertCinema(Cinema cinema) {
        assertNotNull(cinema);
        assertEquals("Silverbird", cinema.name);
        assertNotNull(cinema.location);
        assertEquals(0.00, cinema.location.latitude);
        assertEquals(0.00, cinema.location.longitude);
        assertEquals("Accra Mall", cinema.locationName);
    }

    private String getJsonStrings() {
        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(getCinema());
        return mSerializer.serializationStrategy(List.class).serialize(cinemas);
    }

    private String getJsonString() {
        Cinema cinema = getCinema();
        return mSerializer.serializationStrategy(Cinema.class).serialize(cinema);
    }
}
