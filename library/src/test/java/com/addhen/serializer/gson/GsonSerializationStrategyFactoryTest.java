package com.addhen.serializer.gson;

import com.addhen.serializer.BaseTestCase;
import com.addhen.serializer.Cinema;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class GsonSerializationStrategyFactoryTest extends BaseTestCase {

    @Test
    public void shouldCreateSerializationStrategy() {
        GsonSerializationStrategyFactory factory = GsonSerializationStrategyFactory.create();
        assertNotNull(factory.strategy(Cinema.class));
    }
}
