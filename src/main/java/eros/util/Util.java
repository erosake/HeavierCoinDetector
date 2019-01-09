package eros.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {

	public static <T> List<List<T>> partition(List<T> list,
		int numberOfPartitions) {

		List<List<T>> collect = IntStream.range(0, numberOfPartitions)
			.mapToObj(x -> new ArrayList<T>()).collect(Collectors.toList());

		Stack<T> stack = new Stack<>();
		stack.addAll(list);

		int i = 0;
		while (!stack.isEmpty()) {

			collect.get(i % numberOfPartitions).add(stack.pop());
			++i;

		}

		return collect;

	}

}
