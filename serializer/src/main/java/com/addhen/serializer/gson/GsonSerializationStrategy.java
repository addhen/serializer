package com.addhen.serializer.gson;

import com.google.gson.Gson;

import com.addhen.serializer.SerializationStrategy;

/**
 * @author Henry Addo
 */
public class GsonSerializationStrategy<T> implements SerializationStrategy<T> {

    private final Class<T> mType;

    private final Gson mGson;

    GsonSerializationStrategy(Gson gson, Class<T> type) {
        mGson = gson;
        mType = type;
    }

    @Override
    public String serialize(T value) {
        return mGson.toJson(value, mType);
    }

    @Override
    public T deserialize(String serializedEntity) {
        return mGson.fromJson(serializedEntity, mType);
    }
}
