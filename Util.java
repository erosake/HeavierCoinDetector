package eros.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {

    public static <T> List<List<T>> partition(List<T> list,
                                              int numberOfPartitions) {


        final List<List<T>> emptyPartitions = createEmptyPartitions(numberOfPartitions);


        return populatePartitionsWithListElements(emptyPartitions, list, numberOfPartitions);

    }


    private static <T> List<List<T>> populatePartitionsWithListElements(List<List<T>> emptyPartitions, List<T> list, int numberOfPartitions) {

        final Stack<T> stack = new Stack<>();
        stack.addAll(list);


        T deletedFromStack;
        List<T> partition;

        int i = 0;
        while (!stack.isEmpty()) {

            partition = emptyPartitions.get(i % numberOfPartitions);

            deletedFromStack = stack.pop();
            partition.add(deletedFromStack);

            ++i;

        }

        return emptyPartitions;

    }

    private static <T> List<List<T>> createEmptyPartitions(int numberOfPartitions) {

        final IntFunction<ArrayList<T>> intToNewArrayList = x -> new ArrayList<>();
        return IntStream.range(0, numberOfPartitions)
                .mapToObj(intToNewArrayList).collect(Collectors.toList());

    }

}
