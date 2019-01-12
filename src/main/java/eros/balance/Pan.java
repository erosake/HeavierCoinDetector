package eros.balance;

import eros.data.interfaces.Weightable;

import java.util.ArrayList;
import java.util.List;

class Pan<T extends Weightable> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {

        items.add(item);

    }

    public Double getWeight() {

        return items.stream().map(T::retrieveWeight).reduce(0.0d, Double::sum);

    }

}
