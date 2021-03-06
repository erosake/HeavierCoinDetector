package eros.util;

import eros.data.Coin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static utilities.Utils.generateListOfRandomElementsWithSize;

@SuppressWarnings("ExcessiveLambdaUsage")
@DisplayName("Testing the Util...")
class UtilTests {

    static Stream<Arguments> intAndListProvider() {

        return Stream.of(arguments(
                generateListOfRandomElementsWithSize(x -> UUID.randomUUID(), 3), 3),

                arguments(generateListOfRandomElementsWithSize(
                        x -> generateIntegerBetween1And100(), 3), 3),

                arguments(
                        Arrays.asList(new Coin(2.0d), new Coin(2.0d), new Coin(3.0d)),
                        3),

                arguments(generateListOfRandomElementsWithSize(Random::new, 3), 3));

    }

    private static Integer generateIntegerBetween1And100() {

        var random = new Random();
        return random.nextInt(100) + 1;

    }

    @DisplayName("Testing the partition method...")
    @ParameterizedTest(name = "{index} => {arguments}")
    @ValueSource(ints = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    <T> void partition_Empty_List(int numberOfPartitions) {

        var partition = Util.partition(Collections.emptyList(),
                numberOfPartitions);

        assertTrue(
                (numberOfPartitions > 0) != partition.isEmpty(),
                () -> "Partition is not empty.");

        assertTrue(
                numberOfPartitions <= 0 || partition.size() == numberOfPartitions,
                () -> String.format("Size should be: %s.", numberOfPartitions));

        IntStream.rangeClosed(0, numberOfPartitions - 1)
                .forEach(x -> assertTrue(partition.get(x).isEmpty(),
                        () -> "Size should be 0."));

    }

    @DisplayName("Testing the partition method with a list with 3 elements...")
    @ParameterizedTest(name = "{index} => {arguments}")
    @MethodSource("intAndListProvider")
    <T> void partitionTestListWith3Elements(List<T> list,
                                            int numberOfPartitions) {

        var partition = Util.partition(list, numberOfPartitions);

        assertTrue(
                (numberOfPartitions > 0) != partition.isEmpty(),
                () -> "Partition is not empty.");

        assertTrue(
                numberOfPartitions <= 0 || partition.size() == 3,
                String.format("Size should be: %s.", numberOfPartitions));

        IntStream.rangeClosed(0, numberOfPartitions - 1).forEach(
                x -> assertEquals(1, partition.get(x).size(), "Size should be 1."));

    }

}
