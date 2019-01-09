package eros.generators;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eros.data.Coin;

public class CoinGenerator {

	public List<Coin> generate(int totalNumberOfCoins) {

		if (totalNumberOfCoins < 0) {

			throw new IllegalArgumentException("Only positive values, please.");

		}

		if (totalNumberOfCoins == 0) {

			return Collections.emptyList();

		}

		var random = new Random();
		var weight = random.nextInt(100) * random.nextDouble() + 1;

		IntFunction<Coin> mapper = x -> new Coin(weight);
		var result = IntStream.rangeClosed(1, totalNumberOfCoins - 1)
			.mapToObj(mapper).collect(Collectors.toList());

		result.add(new Coin(weight + random.nextDouble()));

		return result;
	}

}
