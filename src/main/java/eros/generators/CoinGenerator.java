package eros.generators;

import java.util.Collections;
import java.util.List;
import java.util.Random;
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

		Random random = new Random();
		double weight = random.nextInt(100) * random.nextDouble() + 1;

		List<Coin> result = IntStream.rangeClosed(1, totalNumberOfCoins - 1)
			.mapToObj(x -> {
				return new Coin(weight);
			}).collect(Collectors.toList());

		result.add(new Coin(weight + random.nextDouble()));

		return result;
	}

}
