package eros.generators;

import eros.data.Coin;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoinGenerator {

    public List<Coin> generateCoinsWithOneHeavierCoinAmongThem(int totalNumberOfCoins) {

        if (totalNumberOfCoins < 0) {

            throw new IllegalArgumentException("Only positive values, please.");

        }

        if (totalNumberOfCoins == 0) {

            return Collections.emptyList();

        }

        var weight = generateRandomWeight();

        var result = generateIdeticalCoinsWithWeight(totalNumberOfCoins - 1, weight);

        final double addedWeight = new Random().nextDouble();
        result.add(new Coin(weight + addedWeight));

        return result;

    }

    private List<Coin> generateIdeticalCoinsWithWeight(int totalNumberOfCoins, double weight) {

        IntFunction<Coin> mapper = x -> new Coin(weight);

        return IntStream.rangeClosed(1, totalNumberOfCoins)
                .mapToObj(mapper).collect(Collectors.toList());


    }

    private double generateRandomWeight() {

        var random = new Random();
        final int randomInteger = random.nextInt(100);

        return randomInteger * random.nextDouble() + 1;

    }

}
