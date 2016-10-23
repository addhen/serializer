package com.addhen.serializer.moshi;

import com.addhen.serializer.SerializationStrategy;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * @author Henry Addo
 */
public class MoshiSerializationStrategyFactory extends SerializationStrategy.Factory {

    private final Moshi mMoshi;

    private MoshiSerializationStrategyFactory(Moshi moshi) {
        if (moshi == null) {
            throw new NullPointerException("Moshi cannot be null.");
        }
        mMoshi = moshi;
    }

    public static MoshiSerializationStrategyFactory create() {
        return create(new Moshi.Builder().build());
    }

    public static MoshiSerializationStrategyFactory create(Moshi moshi) {
        return new MoshiSerializationStrategyFactory(moshi);
    }

    @Override
    public <T> SerializationStrategy<T> strategy(Class<T> type) {
        JsonAdapter<T> adapter = mMoshi.adapter(type);
        return new MoshiSerializationStrategy<T>(adapter);
    }
}
