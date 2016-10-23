package com.addhen.serializer;


import com.addhen.serializer.gson.GsonSerializationStrategyFactory;

/**
 * @author Henry Addo
 */
public final class Serializer {

    private SerializationStrategy.Factory mFactory;

    Serializer(SerializationStrategy.Factory factory) {
        mFactory = factory;
    }

    public void setSerializationFactory(SerializationStrategy.Factory factory) {
        mFactory = factory;
    }

    public SerializationStrategy.Factory serializationFactory() {
        return mFactory;
    }

    public <T> SerializationStrategy<T> serializationStrategy(Class<T> type) {
        return mFactory.strategy(type);
    }

    public static final class Builder {

        private SerializationStrategy.Factory mFactory;

        private Builder(SerializationStrategy.Factory factory) {
            mFactory = factory;
        }

        public Builder() {
            this(GsonSerializationStrategyFactory.create());
        }

        public Builder serializationStrategyFactory(SerializationStrategy.Factory factory) {
            if (factory == null) {
                throw new NullPointerException("Serialization Strategy factory cannot be null.");
            }
            mFactory = factory;
            return this;
        }

        public Serializer build() {
            return new Serializer(mFactory);
        }
    }
}
