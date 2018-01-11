package com.addhen.serializer.moshi;

import com.addhen.serializer.SerializationStrategy;
import com.squareup.moshi.JsonAdapter;

import java.io.IOException;

/**
 * @author Henry Addo
 */
public final class MoshiSerializationStrategy<T> implements SerializationStrategy<T> {

    private final com.squareup.moshi.JsonAdapter<T> mJsonAdapter;

    public MoshiSerializationStrategy(JsonAdapter<T> adapter) {
        mJsonAdapter = adapter;
    }

    @Override
    public String serialize(T value) {
        return mJsonAdapter.toJson(value);
    }

    @Override
    public T deserialize(String serializedEntity) {
        try {
            return mJsonAdapter.fromJson(serializedEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
