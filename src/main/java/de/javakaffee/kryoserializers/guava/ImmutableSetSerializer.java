package de.javakaffee.kryoserializers.guava;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class ImmutableSetSerializer extends Serializer<ImmutableSet<?>> {

    private static final boolean DOES_NOT_ACCEPT_NULL = false;
    private static final boolean IMMUTABLE = true;

    public ImmutableSetSerializer() {
        super(DOES_NOT_ACCEPT_NULL, IMMUTABLE);
    }

    @Override
    public boolean isImmutable() {
        return IMMUTABLE;
    }

    @Override
    public void write(Kryo kryo, Output output, ImmutableSet<?> object) {
        kryo.writeObject(output, object.asList(), ImmutableListSerializer.SERIALIZER);
    }

    @Override
    public ImmutableSet<?> read(Kryo kryo, Input input, Class<ImmutableSet<?>> type) {

        ImmutableList<?> data = kryo.readObject(input, ImmutableList.class, ImmutableListSerializer.SERIALIZER);
        return ImmutableSet.builder().addAll(data).build();
    }

    public static void registerSerializers(final Kryo kryo) {

        final ImmutableSetSerializer serializer = new ImmutableSetSerializer();

        kryo.register(ImmutableSet.class, serializer);
        kryo.register(ImmutableSet.of(1, 2).getClass(), serializer);
        kryo.register(ImmutableSet.of(1).getClass(), serializer);
        kryo.register(ImmutableSet.of().getClass(), serializer);
    }


}
