package de.javakaffee.kryoserializers.guava;

import static de.javakaffee.kryoserializers.KryoTest.deserialize;
import static de.javakaffee.kryoserializers.KryoTest.serialize;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.esotericsoftware.kryo.Kryo;
import com.google.common.collect.ImmutableSet;

public class ImmutableSetSerializerTest {
    private Kryo _kryo;

    @BeforeTest
    public void beforeTest() {
        _kryo = new Kryo();
        ImmutableSetSerializer.registerSerializers(_kryo);
    }

    @Test( enabled = true )
    public void testImmutableSet() {
        final ImmutableSet<?> obj = ImmutableSet.of(1, 2, 3, 1);
        final byte[] serialized = serialize( _kryo, obj );
        final ImmutableSet<?> deserialized = deserialize(_kryo, serialized, ImmutableSet.class);
        assertEquals(deserialized, obj);
    }

    @Test( enabled = true )
    public void testImmutableSetSingleton() {
        final ImmutableSet<?> obj = ImmutableSet.of(1);
        final byte[] serialized = serialize( _kryo, obj );
        final ImmutableSet<?> deserialized = deserialize(_kryo, serialized, ImmutableSet.class);
        assertEquals(deserialized, obj);
    }

    @Test( enabled = true )
    public void testImmutableSetEmpty() {
        final ImmutableSet<?> obj = ImmutableSet.of();
        final byte[] serialized = serialize( _kryo, obj );
        final ImmutableSet<?> deserialized = deserialize(_kryo, serialized, ImmutableSet.class);
        assertEquals(deserialized, obj);
    }

    // copy

    @Test( enabled = true )
    public void testCopyImmutableSet() {
        final ImmutableSet<?> obj = ImmutableSet.of(1, 2, 3, 1);
        final ImmutableSet<?> copied = _kryo.copy(obj);
        assertSame(copied, obj);
    }

    @Test( enabled = true )
    public void testCopyImmutableSetSingleton() {
        final ImmutableSet<?> obj = ImmutableSet.of(1);
        final ImmutableSet<?> copied = _kryo.copy(obj);
        assertSame(copied, obj);
    }

    @Test( enabled = true )
    public void testCopyImmutableSetEmpty() {
        final ImmutableSet<?> obj = ImmutableSet.of();
        final ImmutableSet<?> copied = _kryo.copy(obj);
        assertSame(copied, obj);
    }



}
