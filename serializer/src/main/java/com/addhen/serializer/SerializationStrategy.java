package com.addhen.serializer;

/**
 * An interface that defines the serialization / deserialization strategy for an object.
 * For example, it can use GSON library as the engine for handling object to a JSON string
 * or from a JSON string to the original object.
 *
 * @author Henry Addo
 */
public interface SerializationStrategy<T> {

    /**
     * Serializes the list of types to it's JSON format
     *
     * @param value The type entity to be serialized
     * @return String the serialized object into a JSON string
     */
    String serialize(T value);

    /**
     * Deserializes a JSON string to it's typed entity
     *
     * @param serializedEntity The serialized object in a JSON string
     * @return The entity type
     */
    T deserialize(String serializedEntity);

    abstract class Factory {

        public abstract <T> SerializationStrategy<T> strategy(Class<T> type);
    }
}
