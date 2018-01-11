package com.addhen.serializer.moshi;

import com.addhen.serializer.BaseTestCase;
import com.addhen.serializer.Cinema;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Henry Addo
 */
public class MoshiSerializationStrategyFactoryTest extends BaseTestCase {

    @Test
    public void shouldCreateMoshiSerializationStrategy() {
        MoshiSerializationStrategyFactory factory = MoshiSerializationStrategyFactory.create();
        assertNotNull(factory.strategy(Cinema.class));
    }
}
