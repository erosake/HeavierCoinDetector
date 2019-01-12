package eros.util.tailcall;

import eros.util.tailcall.interfaces.TailCall;

public class TailCalls {

    public static <T> TailCall<T> done(final T value) {

        return new TailCall<>() {

            @Override
            public boolean isComplete() {

                return true;

            }

            @Override
            public T result() {

                return value;

            }

            @Override
            public TailCall<T> apply() {

                throw new Error("not implemented");

            }

        };

    }

}
