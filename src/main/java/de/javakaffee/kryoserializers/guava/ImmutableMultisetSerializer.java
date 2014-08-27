package de.javakaffee.kryoserializers.guava;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSortedMultiset;

public class ImmutableMultisetSerializer extends Serializer<ImmutableMultiset<?>> {

    private static final boolean DOES_NOT_ACCEPT_NULL = false;
    private static final boolean IMMUTABLE = true;

    public ImmutableMultisetSerializer() {
        super(DOES_NOT_ACCEPT_NULL, IMMUTABLE);
    }

    @Override
    public boolean isImmutable() {
        return IMMUTABLE;
    }

    @Override
    public void write(Kryo kryo, Output output, ImmutableMultiset<?> object) {
        // TODO deal with sorted, comparator, ...
        kryo.writeObject(output, object.asList(), ImmutableListSerializer.SERIALIZER);
    }

    @Override
    public ImmutableMultiset<?> read(Kryo kryo, Input input, Class<ImmutableMultiset<?>> type) {

        ImmutableList<?> data = kryo.readObject(input, ImmutableList.class, ImmutableListSerializer.SERIALIZER);
        return ImmutableMultiset.builder().addAll(data).build();
    }

    public static void registerSerializers(final Kryo kryo) {

        final ImmutableMultisetSerializer serializer = new ImmutableMultisetSerializer();

        // unsorted
        kryo.register(ImmutableMultiset.class, serializer);
        /// RegularImmutableMultiset
        kryo.register(ImmutableMultiset.of().getClass(), serializer);
        /// Keys
        kryo.register(ImmutableMultimap.of(1, 2, 3, 4).keys().getClass(), serializer);

        // sorted
        kryo.register(ImmutableSortedMultiset.class, serializer);
        /// RegularImmutableSortedMultiset
        kryo.register(ImmutableSortedMultiset.of(1).getClass(), serializer);
        /// EmptyImmutableSortedMultiset
        kryo.register(ImmutableSortedMultiset.of().getClass(), serializer);

        // reverse_sorted
        /// DescendingImmutableSortedMultiset
        kryo.register(ImmutableSortedMultiset.of(1).descendingMultiset().getClass(), serializer);
    }

}
