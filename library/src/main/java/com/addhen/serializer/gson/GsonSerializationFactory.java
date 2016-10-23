package com.addhen.serializer.gson;

import com.google.gson.Gson;

import com.addhen.serializer.SerializationStrategy;


/**
 * @author Henry Addo
 */
public final class GsonSerializationFactory extends SerializationStrategy.Factory {

    private final Gson mGson;

    private GsonSerializationFactory(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("Gson cannot be null.");
        }
        mGson = gson;
    }

    public static GsonSerializationFactory create() {
        return create(new Gson());
    }

    public static GsonSerializationFactory create(Gson gson) {
        return new GsonSerializationFactory(gson);
    }

    @Override
    public <T> SerializationStrategy<T> strategy(Class<T> type) {
        return new GsonSerializationStrategy<T>(mGson, type);
    }
}
