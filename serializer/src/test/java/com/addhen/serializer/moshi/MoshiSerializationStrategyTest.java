package com.addhen.serializer.moshi;

import com.addhen.serializer.BaseTestCase;
import com.addhen.serializer.Cinema;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class MoshiSerializationStrategyTest extends BaseTestCase {

    @Test
    public void shouldSuccessfullySerializeToJsonStrings() {
        String jsonString = getJsonStrings();
        assertNotNull(jsonString);
        assertEquals(
                "[{\"location\":{\"latitude\":0.0,\"longitude\":0.0},\"locationName\":"
                        + "\"Accra Mall\",\"name\":\"Silverbird\"}]",
                jsonString);
    }

    @Test
    public void shouldSuccessfullySerializeToJsonString() {
        String jsonString = getJsonString();
        assertNotNull(jsonString);
        assertEquals(
                "{\"location\":{\"latitude\":0.0,\"longitude\":0.0},\"locationName\":"
                        + "\"Accra Mall\",\"name\":\"Silverbird\"}",
                jsonString);
    }

    @Test
    public void shouldSuccessfullyDeserializeStringToJson() {
        String jsonString = getJsonString();
        MoshiSerializationStrategy<Cinema>
                serializationStrategy = getMoshiSerializationStrategy();
        Cinema cinema = serializationStrategy.deserialize(jsonString);
        assertNotNull(cinema);
        assertCinema(cinema);
    }

    @Test
    public void shouldSuccessfullyDeserializeStringToList() {
        String jsonString = getJsonStrings();
        MoshiSerializationStrategy<List<Cinema>>
                serializationStrategy = getListMoshiSerializationStrategy();
        List<Cinema> cinemas = serializationStrategy.deserialize(jsonString);
        assertNotNull(cinemas);
        assertEquals(1, cinemas.size());
        assertCinema(cinemas.get(0));
    }

    private String getJsonStrings() {
        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(getCinema());
        MoshiSerializationStrategy<List<Cinema>> serializationStrategy
                = getListMoshiSerializationStrategy();
        return serializationStrategy.serialize(cinemas);
    }

    private MoshiSerializationStrategy<List<Cinema>> getListMoshiSerializationStrategy() {
        JsonAdapter<List<Cinema>> adapter = new Moshi.Builder().build().adapter(
                Types.newParameterizedType(List.class, Cinema.class));
        return new MoshiSerializationStrategy<>(adapter);
    }

    private String getJsonString() {
        Cinema cinema = getCinema();
        MoshiSerializationStrategy<Cinema> serializationStrategy = getMoshiSerializationStrategy();
        return serializationStrategy.serialize(cinema);
    }

    private MoshiSerializationStrategy<Cinema> getMoshiSerializationStrategy() {
        JsonAdapter<Cinema> adapter = new Moshi.Builder().build().adapter(Cinema.class);
        return new MoshiSerializationStrategy<>(adapter);
    }
}
