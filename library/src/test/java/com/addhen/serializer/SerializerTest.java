package com.addhen.serializer;

import com.addhen.serializer.gson.GsonSerializationStrategyFactory;
import com.addhen.serializer.moshi.MoshiSerializationStrategyFactory;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

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
        assertTrue(mSerializer.serializationFactory() instanceof GsonSerializationStrategyFactory);
        String jsonString = getJsonStrings();
        assertNotNull(jsonString);
        assertEquals(
                "[{\"name\":\"Silverbird\",\"locationName\":\"Accra Mall\",\"location\":"
                        + "{\"latitude\":0.0,\"longitude\":0.0}}]",
                jsonString);
    }

    @Test
    public void shouldUseMoshiToSerialize() {
        MoshiSerializationStrategyFactory factory = MoshiSerializationStrategyFactory.create();
        mSerializer.setSerializationFactory(factory);
        assertTrue(mSerializer.serializationFactory() instanceof MoshiSerializationStrategyFactory);
        String jsonString = getJsonString();
        assertNotNull(jsonString);
        assertEquals(
                "{\"location\":{\"latitude\":0.0,\"longitude\":0.0},\"locationName\":"
                        + "\"Accra Mall\",\"name\":\"Silverbird\"}",
                jsonString);
    }

    @Test
    public void shouldUseMoshiToDeserialize() {
        MoshiSerializationStrategyFactory factory = MoshiSerializationStrategyFactory.create();
        mSerializer.setSerializationFactory(factory);
        assertTrue(mSerializer.serializationFactory() instanceof MoshiSerializationStrategyFactory);
        String jsonString = getJsonStrings();
        List<Cinema> cinemas = Arrays
                .asList(mSerializer.serializationStrategy(Cinema[].class).deserialize(jsonString));
        assertNotNull(cinemas);
        assertEquals(1, cinemas.size());
        assertCinema(cinemas.get(0));

    }

    @Test
    public void shouldSuccessfullySerializeToJsonString() {
        assertTrue(mSerializer.serializationFactory() instanceof GsonSerializationStrategyFactory);
        String jsonString = getJsonString();
        assertNotNull(jsonString);
        assertEquals(
                "{\"name\":\"Silverbird\",\"locationName\":\"Accra Mall\",\"location\":"
                        + "{\"latitude\":0.0,\"longitude\":0.0}}",
                jsonString);
    }

    @Test
    public void shouldSuccessfullyDeserializeStringToJson() {
        assertTrue(mSerializer.serializationFactory() instanceof GsonSerializationStrategyFactory);
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
