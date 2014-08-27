package de.javakaffee.kryoserializers.guava;

import static de.javakaffee.kryoserializers.KryoTest.deserialize;
import static de.javakaffee.kryoserializers.KryoTest.serialize;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.esotericsoftware.kryo.Kryo;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;

public class ImmutableMultisetSerializerTest {
    private Kryo _kryo;

    @BeforeTest
    public void beforeTest() {
        _kryo = new Kryo();
        ImmutableCollectionSerializer.registerSerializers(_kryo);
    }


    @Test( enabled = true )
    public void testImmutableMultiset() {
        final ImmutableMultiset<?> obj = ImmutableMultiset.of(1, 2, 3, 1);
        final byte[] serialized = serialize( _kryo, obj );
        final ImmutableMultiset<?> deserialized = deserialize(_kryo, serialized, ImmutableMultiset.class);
        assertEquals(deserialized, obj);
    }

    @Test( enabled = true )
    public void testImmutableMultisetSingleton() {
        final ImmutableMultiset<?> obj = ImmutableMultiset.of(1);
        final byte[] serialized = serialize( _kryo, obj );
        final ImmutableMultiset<?> deserialized = deserialize(_kryo, serialized, ImmutableMultiset.class);
        assertEquals(deserialized, obj);
    }

    @Test( enabled = true )
    public void testImmutableMultisetEmpty() {
        final ImmutableMultiset<?> obj = ImmutableMultiset.of();
        final byte[] serialized = serialize( _kryo, obj );
        final ImmutableMultiset<?> deserialized = deserialize(_kryo, serialized, ImmutableMultiset.class);
        assertEquals(deserialized, obj);
    }


    @Test( enabled = true )
    public void testImmutableMultisetKeys() {
        final ImmutableMultiset<?> obj = ImmutableMultimap.of(1, 2, 3, 4).keys();
        final byte[] serialized = serialize( _kryo, obj );
        final ImmutableMultiset<?> deserialized = deserialize(_kryo, serialized, ImmutableMultiset.class);
        assertEquals(deserialized, obj);
    }

    // copy

    @Test( enabled = true )
    public void testCopyImmutableMultiset() {
        final ImmutableMultiset<?> obj = ImmutableMultiset.of(1, 2, 3, 1);
        final ImmutableMultiset<?> copied = _kryo.copy(obj);
        assertSame(copied, obj);
    }

    @Test( enabled = true )
    public void testCopyImmutableMultisetSingleton() {
        final ImmutableMultiset<?> obj = ImmutableMultiset.of(1);
        final ImmutableMultiset<?> copied = _kryo.copy(obj);
        assertSame(copied, obj);
    }

    @Test( enabled = true )
    public void testCopyImmutableMultisetEmpty() {
        final ImmutableMultiset<?> obj = ImmutableMultiset.of();
        final ImmutableMultiset<?> copied = _kryo.copy(obj);
        assertSame(copied, obj);
    }

    @Test( enabled = true )
    public void testCopyImmutableMultisetKeys() {
        final ImmutableMultiset<?> obj = ImmutableMultimap.of(1, 2, 3, 4).keys();
        final ImmutableMultiset<?> copied = _kryo.copy(obj);
        assertSame(copied, obj);
    }

}
