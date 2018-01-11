package com.addhen.serializer.gson;

import com.google.gson.Gson;

import com.addhen.serializer.SerializationStrategy;


/**
 * @author Henry Addo
 */
public final class GsonSerializationStrategyFactory extends SerializationStrategy.Factory {

    private final Gson mGson;

    private GsonSerializationStrategyFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("Gson cannot be null.");
        }
        mGson = gson;
    }

    public static GsonSerializationStrategyFactory create() {
        return create(new Gson());
    }

    public static GsonSerializationStrategyFactory create(Gson gson) {
        return new GsonSerializationStrategyFactory(gson);
    }

    @Override
    public <T> SerializationStrategy<T> strategy(Class<T> type) {
        return new GsonSerializationStrategy<T>(mGson, type);
    }
}
