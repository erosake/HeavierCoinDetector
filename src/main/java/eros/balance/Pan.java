package eros.balance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eros.data.interfaces.Weightable;

public class Pan<T extends Weightable> {

	private List<T> items = new ArrayList<>();

	public void add(T item) {

		items.add(item);

	}

	public List<T> getItems() {

		return Collections.unmodifiableList(items);

	}

	public Double getWeight() {

		return items.stream().map(T::retrieveWeight).reduce(0.0d, Double::sum);

	}

}
