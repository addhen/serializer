package com.addhen.serializer.gson;

import com.google.gson.Gson;

import com.addhen.serializer.BaseTestCase;
import com.addhen.serializer.Cinema;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class GsonSerializationStrategyTest extends BaseTestCase {

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
        GsonSerializationStrategy<Cinema>
                serializationStrategy = getGsonSerializationStrategy();
        Cinema cinema = serializationStrategy.deserialize(jsonString);
        assertNotNull(cinema);
        assertCinema(cinema);
    }

    @Test
    public void shouldSuccessfullyDeserializeStringToList() {
        String jsonString = getJsonStrings();
        GsonSerializationStrategy<Cinema[]>
                serializationStrategy = new GsonSerializationStrategy<>(new Gson(), Cinema[].class);
        List<Cinema> cinemas = Arrays.asList(serializationStrategy.deserialize(jsonString));
        assertNotNull(cinemas);
        assertEquals(1, cinemas.size());
        assertCinema(cinemas.get(0));
    }

    private String getJsonStrings() {
        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(getCinema());
        GsonSerializationStrategy<List> serializationStrategy = getListGsonSerializationStrategy();
        return serializationStrategy.serialize(cinemas);
    }

    private GsonSerializationStrategy<List> getListGsonSerializationStrategy() {
        return new GsonSerializationStrategy<>(new Gson(), List.class);
    }

    private String getJsonString() {
        Cinema cinema = getCinema();
        GsonSerializationStrategy<Cinema> serializationStrategy = getGsonSerializationStrategy();
        return serializationStrategy.serialize(cinema);
    }

    private GsonSerializationStrategy<Cinema> getGsonSerializationStrategy() {
        return new GsonSerializationStrategy<>(new Gson(), Cinema.class);
    }
}
