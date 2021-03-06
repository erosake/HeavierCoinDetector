package eros.util.tailcall.interfaces;

import java.util.stream.Stream;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@FunctionalInterface
public interface TailCall<T> {

    TailCall<T> apply();

    default boolean isComplete() {

        return false;

    }

    default T result() {

        throw new Error("not implemented");

    }

    default T get() {

        return Stream.iterate(this, TailCall::apply)
                .filter(TailCall::isComplete).findFirst().get().result();

    }

}